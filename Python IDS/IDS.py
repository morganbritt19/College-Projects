# This was a proof-of-concept IDS system designed for my Intrusion Detection/Prevention class. It is written in Python and leverages Snort for identification and rule-based filtering. It takes a CSV file as a ruleset and then analyzes packets, flagging them based on the configuration. 

import subprocess
import csv

def read_csv_file(filename):
    # Reads a CSV file and provides a list of dictionaries with each dictionary representing a row within the file
    with open(filename, "r") as csvfile:
        reader = csv.DictReader(csvfile)
        return list(reader)

def run_snort_capture(interface):
    # Runs the Snort packet capture on the specified interface.
    command = ["snort", "-i", interface]
    return subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

def parse_snort_alert(alert):
    # Parses a Snort alert message and returns a dictionary containing the alert information
    parts = alert.strip().split(" ")
    return {
        "timestamp": parts[0],
        "priority": parts[1],
        "classification": parts[2],
        "source_ip": parts[3],
        "destination_ip": parts[5],
        "message": " ".join(parts[6:])
    }

def apply_rules(alert, ruleset_rows):
    # Applies the rules in the ruleset to the alert data and returns True if the alert matches a rule, otherwise returns False
    for rule in ruleset_rows:
        if (alert["source_ip"] == rule["source_ip"]
                and alert["destination_ip"] == rule["destination_ip"]
                and alert["classification"] == rule["classification"]):
            if rule["action"] == "block":
                return True
    return False

# Specify interface for capture and ruleset for packet analysis
interface = "eth0"
ruleset_filename = "ruleset.csv"

ruleset_rows = read_csv_file(ruleset_filename)

# Run the Snort packet capture
snort_proc = run_snort_capture(interface)

# Process Snort alerts
for alert in snort_proc.stdout:
    alert_data = parse_snort_alert(alert.decode())
    if apply_rules(alert_data, ruleset_rows):
        print("ALERT: Blocked packet detected!")
        print(alert_data)
    else:
        print("Packet passed through without any issue!")
        print(alert_data)
