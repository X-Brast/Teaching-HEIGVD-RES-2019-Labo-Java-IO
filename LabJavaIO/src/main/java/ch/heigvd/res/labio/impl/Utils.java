package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {

    String[] result = new String[2];
    int posSlashR = lines.indexOf('\r');
    int posSlashN = lines.indexOf('\n');

    if(posSlashN == -1 && posSlashR == -1){
      result[0] = "";
      result[1] = lines;
    }
    else if(posSlashN > -1 && posSlashR == -1){
      result[0] = lines.substring(0, posSlashN+1);
      result[1] = lines.substring(posSlashN+1, lines.length());
    }
    else if(posSlashN == -1 && posSlashR > -1){
      result[0] = lines.substring(0, posSlashR+1);
      result[1] = lines.substring(posSlashR+1, lines.length());
    }
    else if(posSlashN > -1 && posSlashR > -1){
      if(posSlashN < posSlashR){
        result[0] = lines.substring(0, posSlashN+1);
        result[1] = lines.substring(posSlashN+1, lines.length());
      }
      else{
        if(posSlashN == posSlashR+1){
          result[0] = lines.substring(0, posSlashN+1);
          result[1] = lines.substring(posSlashN+1, lines.length());
        }
        else{
          result[0] = lines.substring(0, posSlashR+1);
          result[1] = lines.substring(posSlashR+1, lines.length());
        }
      }
    }

    return result;

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
