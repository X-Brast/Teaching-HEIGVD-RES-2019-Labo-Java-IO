package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    super.write(str.toUpperCase(), off, len);

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for(int i = off; i < len + off; ++i){
      if(cbuf[i] >= 97 && cbuf[i] <= 122)
        cbuf[i] -= 32;
    }

    super.write(cbuf, off, len);

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {

    if(c >= 97 && c <= 122)
      c -= 32;

    super.write(c);

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
