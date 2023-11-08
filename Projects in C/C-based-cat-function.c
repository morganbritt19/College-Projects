// This is your standard input/output and standard library things. I would make a habit of including these most of the time. Your IDE will tell you if you don't need them. Probably.
#include <stdio.h>
#include <stdlib.h>

// This is where you declare your display_stream function that will output the contents of a file selected.
// Coleman says its a prototype function to be defined later in the assignment sheet.
void display_stream(FILE *file);

// Main function
// This basically allows the function to take command line arguments. Kinda like how nmap allows you to specify an IP address to scan, this program will take a file as input.
int main(int argc, char *argv[]) {
    // This statement basically means that that, if there isn't any file input, it'll throw and error with the if statement below.
    if (argc > 1) {
        // This is a basic for loop that will iterate i through every function pass.
        for (int i = 1; i < argc; i++) { 
        // This will open the file a read the contents which will later be printed on the screen with the display_stream function.
        FILE *file = fopen(argv[i], "r"); 
        // This function will display a standard error output if there is nothing inside to display. This could probably be changed for the purpose of your assignment.
        if (file == NULL) {
            // You should probably change this.
            fprintf(stderr, "Error: Bruh that file ain't real. '%s'\n", argv[i]); 
            exit(1);
        }
        // This is basically to call the function defined earlier to display the contents of a file the closing it.
        display_stream(file);
        fclose(file);
    }

    } else {
        // This will read standard input until the end of the file
    display_stream(stdin);
    }
    // Idfk why every C program has to have this return function, but it should always be placed at the end of the main function, and that's pretty universal. You'll have to do that for any other programs you write.
    return 0;
}

// This basically just gets each character of the file that was input and spits it out onto the screen until it hits the end of the file (EOF).
void display_stream(FILE *file){
    int ch; 
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);
    }
}
