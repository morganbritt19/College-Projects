# This was a proof-of-concept IDS system designed for my Intrusion Detection/Prevention class. It is written in Python and leverages Snort for identification and rule-based filtering. It takes a CSV file as a ruleset and then analyzes packets, flagging them based on the configuration. 

import subprocess
import csv
import logging
import argparse

# Function to read a CSV file and return a list of dictionaries
def read_csv_file(filename):
    with open(filename, "r") as csvfile:
        reader = csv.DictReader(csvfile)
        return list(reader)

# Function to run Snort packet capture on a specified network interface
def run_snort_capture(interface):
    command = ["snort", "-i", interface]
    return subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

# Function to parse a Snort alert message and return a dictionary with alert information
def parse_snort_alert(alert):
    parts = alert.strip().split(" ")
    return {
        "timestamp": parts[0],
        "priority": parts[1],
        "classification": parts[2],
        "source_ip": parts[3],
        "destination_ip": parts[5],
        "message": " ".join(parts[6:])
    }

# Function to apply rules in the ruleset to the alert data and return True if a rule is matched
def apply_rules(alert, ruleset_rows):
    for rule in ruleset_rows:
        if (alert["source_ip"] == rule["source_ip"]
                and alert["destination_ip"] == rule["destination_ip"]
                and alert["classification"] == rule["classification"]):
            if rule["action"] == "block":
                return True
    return False

# Main function that orchestrates the IDS functionality
def main(interface, ruleset_filename):
    # Configure logging to a file
    logging.basicConfig(filename="ids.log", level=logging.INFO, format="%(asctime)s [%(levelname)s] %(message)s")
    logging.info("Starting IDS with interface: %s", interface)

    # Read the ruleset from a CSV file
    ruleset_rows = read_csv_file(ruleset_filename)

    # Start Snort packet capture
    snort_proc = run_snort_capture(interface)

    # Process Snort alerts as they come in
    for alert in snort_proc.stdout:
        alert_data = parse_snort_alert(alert.decode())
        if apply_rules(alert_data, ruleset_rows):
            logging.warning("ALERT: Blocked packet detected! %s", alert_data)
        else:
            logging.info("Packet passed through without any issue! %s", alert_data)

# Entry point for the script
if __name__ == "__main__":
    # Parse command-line arguments using argparse
    parser = argparse.ArgumentParser(description="Intrusion Detection System (IDS) Proof of Concept")
    parser.add_argument("-i", "--interface", default="eth0", help="Network interface for packet capture")
    parser.add_argument("-r", "--ruleset", default="ruleset.csv", help="CSV ruleset file")
    args = parser.parse_args()

    # Call the main function with the specified interface and ruleset file
    main(args.interface, args.ruleset)
