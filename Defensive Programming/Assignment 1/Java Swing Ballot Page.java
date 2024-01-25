/*
- This is designed ot be paired with the Java Swing Auth Page in the same folder
- Essentially, the assignment outlined that we were supposed to create an authentication page for a voting system and a ballot selection page for casting votes
- This offers two options for President and two options for Vice President, though that can be extended/edited as needed
- Requires an option in both fields to be selected before submission is successful
- Submitting a vote doesn't actually do anything, but there is potential to add a back end that could add further functionality
- Realistically, this is a proof-of-concept that offers very little functionality
*/

// Ballot Class that calls the BallotFrame class
public class Ballot {

    public static void main(String[] args) {
        // Instantiate the BallotFrame
        javax.swing.SwingUtilities.invokeLater(BallotFrame::new);
    }
}


// BallotFrame Class
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallotFrame extends JFrame {

    private JRadioButton selectedPresidentialCandidate;
    private JRadioButton selectedVicePresidentialCandidate;
    private JButton submitButton; // Declare submitButton as an instance variable

    public BallotFrame() {
        // This sets up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Hacker's Club Election System");
        setLayout(new BorderLayout());

        // Header information
        JLabel headerLabel = new JLabel("Cast Your Vote!", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Presidential Candidates
        JPanel presidentialPanel = createCandidatePanel("Presidential Candidate", new String[]{"Candidate 1", "Candidate 2"});
        add(presidentialPanel, BorderLayout.CENTER);

        // Vice Presidential Candidates
        JPanel vicePresidentialPanel = createCandidatePanel("Vice Presidential Candidate", new String[]{"Candidate 1", "Candidate 2"});
        add(vicePresidentialPanel, BorderLayout.SOUTH);

        // Submit button (added to the vicePresidentialPanel)
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false); // Initially disabled until both candidates are selected
        vicePresidentialPanel.add(submitButton); // Add to the vicePresidentialPanel

        // ActionListener for the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if both presidential and vice-presidential candidates are selected
                if (selectedPresidentialCandidate != null && selectedVicePresidentialCandidate != null) {
                    // Display a message indicating successful vote submission
                    JOptionPane.showMessageDialog(BallotFrame.this, "Vote Submitted Successfully");
                    // Close the frame
                    dispose();
                } else {
                    // Display an error message if not all candidates are selected
                    JOptionPane.showMessageDialog(BallotFrame.this, "Please select both presidential and vice-presidential candidates");
                }
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    private JPanel createCandidatePanel(String title, String[] candidates) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ButtonGroup buttonGroup = new ButtonGroup();

        for (String candidate : candidates) {
            JRadioButton radioButton = new JRadioButton(candidate);
            buttonGroup.add(radioButton);
            panel.add(radioButton);

            // ActionListener for the radio button
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (title.equals("Presidential Candidate")) {
                        selectedPresidentialCandidate = radioButton.isSelected() ? radioButton : null;
                    } else if (title.equals("Vice Presidential Candidate")) {
                        selectedVicePresidentialCandidate = radioButton.isSelected() ? radioButton : null;
                    }

                    // Enable the submit button only if both candidates are selected
                    submitButton.setEnabled(selectedPresidentialCandidate != null && selectedVicePresidentialCandidate != null);
                }
            });
        }

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BallotFrame::new);
    }
}
