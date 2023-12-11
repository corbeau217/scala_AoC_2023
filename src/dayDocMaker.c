#include <stdio.h>

// use this to run it:
//  make dayDocMaker && ./dayDocMaker

#define FILENAMEPATTERN "../docs/days/day%d.md"
#define FILECONTENTSPATTERN "# day %d: scala\nscala attempt at day %d\n## part 1\n### strategy\n* ...\n"



void generateDocument(int dayNumber){
  char fileNameBuffer[25];
  FILE *creatingFile;

  sprintf(fileNameBuffer,FILENAMEPATTERN,dayNumber);

  // open the file to be made
  creatingFile = fopen(fileNameBuffer,"w+");

  // make sure it worked
  if(creatingFile){
    // have file to work with
    fprintf(creatingFile,FILECONTENTSPATTERN,dayNumber,dayNumber);
    // close the file
    fclose(creatingFile);
  }
  else {
    // failed to open/create
    printf("failed to open/create file: %s\n",fileNameBuffer);
  }
}

int main(int argc, char**argv){
  // https://www.ibm.com/docs/en/i/7.1?topic=functions-fopen-open-files
  // bruv, we were destroying it on write for that file in the CTF :blobsob:

  int i;

  for(i = 6; i <= 31; i++){
    generateDocument(i);
  }

  return 0;
}