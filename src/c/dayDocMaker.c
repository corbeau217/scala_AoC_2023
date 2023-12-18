#include <stdio.h>

// use this to run it:
//  make dayDocMaker && ./dayDocMaker

// ==============================================================================================================
// ==============================================================================================================
// ========== SHARED DEFINTIONS

// this is for escaping the subfolder we're in to get to src folder
#define FILEPATH_RELATIVEPART_UPTOSRC "../"
// this is for escaping the subfolder we're in up to the workspace folder
#define FILEPATH_RELATIVEPART_UPTOWORKSPACE "../../"

// the last AoC file we'd have
#define LASTPOSSIBLE_AOC_FILENUMBER 25

#define STRING_PATTERN "%s"

// ==============================================================================================================
// ==============================================================================================================

// ==============================================================================================================
// ==============================================================================================================
// ========== MARKDOWN DEFINITIONS

// for when we are looking from workspace folder
//  different subfolder so we had it all in the pattern
#define FILEPATH_MD_RELATIVEPART_FROMWORKSPACE ""

// file path pattern for markdown files
#define FILEPATH_MD_PATTERN "%sdocs/days/day%d.md"

// markdown first untouched
#define FIRSTUNTOUCHED_MD_FILENUMBER 9

// title and description
#define FILECONTENTS_TITLEANDDESC "# day %d: scala\nscala attempt at day %d"

// the quicklinks portion
//  this could probably have some of its redundancy removed by breaking it up
//  which would allow for new files to be handled easier
#define FILECONTENTS_QUICKLINKS_TITLE "\n## Quick links"
#define FILECONTENTS_QUICKLINKS_REPOLINKS "\n### REPO NAV\n* [`README`](./README.md)\n* [`projectChangesAndNotes`](./projectChangesAndNotes.md)\n* [`dayDocMaker`](./dayDocMaker.md)"
#define FILECONTENTS_QUICKLINKS_DAYLINKS "\n### DAY FILES\n* [AoC page](https://adventofcode.com/2023/day/%d)\n* [Input file](https://adventofcode.com/2023/day/%d/input)\n* [Scala file](../../src/main/scala/day%d.scala)"

// the portion where we include out details
//  this is just to push towards a particular forma
#define FILECONTENTS_PARTSTUBS "\n## part 1\n### strategy\n1. ...\n## part 2\n### strategy\n1. ...\n"

// ==============================================================================================================
// ========== MARKDOWN GENERATION FUNCTION

void generateDaysMarkdownDocument(int dayNumber,const char *fileNamePath){
  FILE *creatingFile;

  // open the file to be made
  creatingFile = fopen(fileNamePath,"w+");

  // make sure it worked
  if(creatingFile){
    // mention the file we're updating
    printf(STRING_PATTERN,"updating: '");
    printf(FILEPATH_MD_PATTERN,FILEPATH_MD_RELATIVEPART_FROMWORKSPACE,dayNumber);
    printf(STRING_PATTERN,"'\n");
    // have file to work with
    // ------------------------------------------------------------------------------------------
    
    // do the top part
    fprintf(creatingFile,     FILECONTENTS_TITLEANDDESC,        dayNumber,dayNumber);
    // now the quicklinks portion
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_QUICKLINKS_TITLE     );
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_QUICKLINKS_REPOLINKS );
    fprintf(creatingFile,     FILECONTENTS_QUICKLINKS_DAYLINKS, dayNumber,dayNumber,dayNumber);
    // now the rest
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_PARTSTUBS            );

    // ------------------------------------------------------------------------------------------
    // close the file
    fclose(creatingFile);
  }
  else {
    // failed to open/create
    printf("failed to open/create file: %s\n",fileNamePath);
  }
}

// ==============================================================================================================
// ==============================================================================================================

// ==============================================================================================================
// ==============================================================================================================
// ========== SCALA DEFINITIONS

// for when we are looking from workspace folder
//  similar subfolder so we some in the pattern
#define FILEPATH_SCALA_RELATIVEPART_FROMWORKSPACE "src/"

// file path pattern for scala files
#define FILEPATH_SCALA_PATTERN "%smain/scala/day%d.scala"

// scala first untouched
#define FIRSTUNTOUCHED_SCALA_FILENUMBER 5


#define FILECONTENTS_SCALA_METHODSPACER "\n  // ========================================\n  // ========================================\n"
// takes ( dayNum:int, dayNum:int, spacerString:str )
#define FILECONTENTS_SCALA_OBJECTFILE_START "package day%d\n\nimport scala.io.Source\nimport Main._\n\nobject Day%d {%s\n"

#define FILECONTENTS_SCALA_HANDLEDAY_METHODSTART "  /**\n    * inlet for the code\n    *\n    * @param partNumber given as Int from caller\n    */\n  def handleDay(partNumber:Int):Unit={\n"

#define FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE "          // ============================================================\n"
#define FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_START "    try{\n      partNumber match {\n"

// takes ( spacerStr:str, dayNum:int, dayNum:int, spacerString:str )
#define FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_TESTING "        // testing addition\n        case 99 => {\n%s          // ...\n          handlePart1(Main.grabLinesFromFile(\"data/day%dtestinput1.txt\"),true)\n          // handlePart2(Main.grabLinesFromFile(\"data/day%dtestinput1.txt\"),true)\n          // ...\n%s        }\n        // the part matching\n"

// takes ( partNum:int, spacerStr:str, partNum:int, dayNum:int, spacerString:str )
#define FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_PARTSTUB "        case %d => {\n%s          handlePart%d(Main.grabLinesFromFile(\"data/day%dinput.txt\"),false)\n%s        }\n"

// takes ( spacerStr:str, dayNum:int, spacerString:str, dayNum:int )
#define FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_END "        case numberInput => {\n%s          Main.failingMessage(\"DAY %d INVALID PART NUMBER: \"+numberInput)\n%s        }\n      }\n    }\n    catch {\n      case e:Exception=> Main.failingMessage(\"DAY %d HAD EXCEPTION: \"+e.toString())\n    }"

#define FILECONTENTS_SCALA_HANDLEDAY_METHODEND "\n  }\n"
  // ========================================
  // ========================================

// takes ( partNum:int, dayNum:int, partNum:int )
#define FILECONTENTS_SCALA_HANDLEPART_METHODSTUB "\n  def handlePart%d(inputLines:List[String],includeDebuggingInfo:Boolean):Unit={\n    // TODO: DAY %d PART %d\n  }\n"
  
  // ========================================
  // ========================================

#define FILECONTENTS_SCALA_OBJECTFILE_END "}"

// ==============================================================================================================
// ========== SCALA GENERATION FUNCTION

void generateDaysScalaDocument(int dayNumber,const char *fileNamePath){
  FILE *creatingFile;

  // open the file to be made
  creatingFile = fopen(fileNamePath,"w+");

  // make sure it worked
  if(creatingFile){
    // mention the file we're updating
    printf(STRING_PATTERN,"updating: '");
    printf(FILEPATH_SCALA_PATTERN,FILEPATH_SCALA_RELATIVEPART_FROMWORKSPACE,dayNumber);
    printf(STRING_PATTERN,"'\n");
    // have file to work with
    // ------------------------------------------------------------------------------------------

    // do the top part
    // takes ( dayNum:int, dayNum:int, spacerString:str )
    fprintf(creatingFile,FILECONTENTS_SCALA_OBJECTFILE_START,                       dayNumber,dayNumber,FILECONTENTS_SCALA_METHODSPACER);
    
    // ---------------------------------------------------------------------------------

    // now the day handling code
    // documentation/method start first
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_HANDLEDAY_METHODSTART);
    // start the pattern matching
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_START);
    // add the testing match case
    //  takes ( spacerStr:str, dayNum:int, dayNum:int, spacerString:str )
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_TESTING,            FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE,dayNumber,dayNumber,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE );
    // add in both parts
    //  takes ( partNum:int, spacerStr:str, partNum:int, dayNum:int, spacerString:str )
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_PARTSTUB,            1,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE,1,dayNumber,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE );
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_PARTSTUB,            2,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE,2,dayNumber,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE );
    // end the patter matching
    //  takes ( spacerStr:str, dayNum:int, spacerString:str, dayNum:int )
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_END,                 FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE,dayNumber,FILECONTENTS_SCALA_HANDLEDAY_PARTMATCH_BREAKERLINE,dayNumber);
    // end the handle day method
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_HANDLEDAY_METHODEND);

    // ---------------------------------------------------------------------------------

    // add in a method spacer
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_METHODSPACER);
    // handle part addition
    //  takes ( partNum:int )
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEPART_METHODSTUB,                   1,dayNumber,1);
    // add in a method spacer
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_METHODSPACER);
    // handle part addition
    //  takes ( partNum:int )
    fprintf(creatingFile,FILECONTENTS_SCALA_HANDLEPART_METHODSTUB,                   2,dayNumber,2);
    // add in a method spacer
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_METHODSPACER);

    // now finish the file
    fprintf(creatingFile,STRING_PATTERN,FILECONTENTS_SCALA_OBJECTFILE_END);

    // ------------------------------------------------------------------------------------------
    // close the file
    fclose(creatingFile);
  }
  else {
    // failed to open/create
    printf("failed to open/create file: %s\n",fileNamePath);
  }
}

// ==============================================================================================================
// ==============================================================================================================

// ==============================================================================================================
// ==============================================================================================================
// ========== INPUT DEFINITIONS

// for when we are looking from workspace folder
//  different subfolder so we had it all in the pattern
#define FILEPATH_INPUT_RELATIVEPART_FROMWORKSPACE ""

// file path pattern for input files
#define FILEPATH_INPUT_PATTERN "%sdata/day%dinput.txt"
#define FILEPATH_TESTINPUT_PATTERN "%sdata/day%dtestinput1.txt"

// INPUT first untouched
#define FIRSTUNTOUCHED_INPUT_FILENUMBER 6

// ==============================================================================================================
// ========== INPUT GENERATION FUNCTION

void generateDaysInputDocument(int dayNumber,const char *fileNamePath){
  FILE *creatingFile;

  // open the file to be made
  creatingFile = fopen(fileNamePath,"w+");

  // make sure it worked
  if(creatingFile){
    // mention the file we're updating
    printf(STRING_PATTERN,"updating: '");
    printf(FILEPATH_INPUT_PATTERN,FILEPATH_INPUT_RELATIVEPART_FROMWORKSPACE,dayNumber);
    printf(STRING_PATTERN,"'\n");
    // have file to work with
    // ------------------------------------------------------------------------------------------

    // print nothing to make sure it's working
    fprintf(creatingFile,STRING_PATTERN,"\n");

    // ------------------------------------------------------------------------------------------
    // close the file
    fclose(creatingFile);
  }
  else {
    // failed to open/create
    printf("failed to open/create file: %s\n",fileNamePath);
  }
}
void generateDaysTestInputDocument(int dayNumber,const char *fileNamePath){
  FILE *creatingFile;

  // open the file to be made
  creatingFile = fopen(fileNamePath,"w+");

  // make sure it worked
  if(creatingFile){
    // mention the file we're updating
    printf(STRING_PATTERN,"updating: '");
    printf(FILEPATH_TESTINPUT_PATTERN,FILEPATH_INPUT_RELATIVEPART_FROMWORKSPACE,dayNumber);
    printf(STRING_PATTERN,"'\n");
    // have file to work with
    // ------------------------------------------------------------------------------------------

    // print nothing to make sure it's working
    fprintf(creatingFile,STRING_PATTERN,"\n");

    // ------------------------------------------------------------------------------------------
    // close the file
    fclose(creatingFile);
  }
  else {
    // failed to open/create
    printf("failed to open/create file: %s\n",fileNamePath);
  }
}

// ==============================================================================================================
// ==============================================================================================================

int main(int argc, char**argv){
  // https://www.ibm.com/docs/en/i/7.1?topic=functions-fopen-open-files
  // bruv, we were destroying it on write for that file in the CTF :blobsob:

  int i;

  char fileNameBuffer[34];
  for(i = 1; i <= LASTPOSSIBLE_AOC_FILENUMBER; i++){
    // ------------------------------------------------------
    if(FIRSTUNTOUCHED_MD_FILENUMBER<=i){
      // can do markdown
      sprintf(fileNameBuffer,FILEPATH_MD_PATTERN,FILEPATH_RELATIVEPART_UPTOWORKSPACE,i);
      generateDaysMarkdownDocument(i,fileNameBuffer);
    }
    // ------------------------------------------------------
    // ------------------------------------------------------
    if(FIRSTUNTOUCHED_SCALA_FILENUMBER<=i){
      // can do scala
      sprintf(fileNameBuffer,FILEPATH_SCALA_PATTERN,FILEPATH_RELATIVEPART_UPTOSRC,i);
      generateDaysScalaDocument(i,fileNameBuffer);
    }
    // ------------------------------------------------------
    // ------------------------------------------------------
    if(FIRSTUNTOUCHED_INPUT_FILENUMBER<=i){
      // can do input
      sprintf(fileNameBuffer,FILEPATH_INPUT_PATTERN,FILEPATH_RELATIVEPART_UPTOWORKSPACE,i);
      generateDaysInputDocument(i,fileNameBuffer);
      sprintf(fileNameBuffer,FILEPATH_TESTINPUT_PATTERN,FILEPATH_RELATIVEPART_UPTOWORKSPACE,i);
      generateDaysTestInputDocument(i,fileNameBuffer);
    }
    // ------------------------------------------------------
  }

  return 0;
}