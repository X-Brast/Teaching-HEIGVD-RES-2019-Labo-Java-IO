package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int counter = 1;
  private boolean first = true;
  private boolean slashR = false;
  private boolean slashN = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String result = "";
    if(first){
      first = false;
      result = counter++ + "\t";
    }

    for(int i = off; i < len + off; i++) {
      if(str.charAt(i) == '\n') {
        result += "\n" + counter++ + "\t";
        slashR = false;
      }
      else if(str.charAt(i) == '\r') {
        result += str.charAt(i);
        slashR = true;
      }
      else {
        if(slashR){
          result += counter++ + "\t";
          slashR = false;
        }
        result += str.charAt(i);
      }
    }

    super.write(result, 0, result.length());

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    String result = "";
    if(first){
      first = false;
      result = counter++ + "\t";
    }

    for(int i = off; i < len + off; i++) {
      if(cbuf[i] == '\n') {
        result += "\n" + counter++ + "\t";
        slashR = false;
      }
      else if(cbuf[i] == '\r') {
        result += cbuf[i];
        slashR = true;
      }
      else {
        if(slashR){
          result += counter++ + "\t";
          slashR = false;
        }
        result += cbuf[i];
      }
    }

    super.write(result, 0, result.length());

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {

    String result = "";
    if(first){
      first = false;
      result = counter++ + "\t";
    }

    if(c == '\n') {
      result += "\n" + counter++ + "\t";
      slashR = false;
    }
    else if(c == '\r'){
      result += (char)c;
      slashR = true;
    }
    else{
      if(slashR){
        result += counter++ + "\t";
        slashR = false;
      }
      result += (char)c;
    }

    super.write(result, 0, result.length());

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
