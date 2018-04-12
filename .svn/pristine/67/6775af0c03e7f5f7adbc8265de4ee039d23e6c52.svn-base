package org.my431.platform.filter;

import java.io.CharArrayWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class FileCaptureResponseWrapper extends HttpServletResponseWrapper
{
private CharArrayWriter output;
public String toString() { return output.toString(); }
public FileCaptureResponseWrapper(HttpServletResponse response) {
super(response);
output = new CharArrayWriter();
}
public PrintWriter getWriter() {
return new PrintWriter(output);
}
public void writeFile(String fileName) throws IOException {

OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");  
outputStream.write(output.toCharArray());  
//FileWriter fw = new FileWriter(fileName);
//fw.write( output.toCharArray() );
outputStream.close();
}

public void writeResponse(PrintStream out) {
out.print( output.toCharArray() );
}
}