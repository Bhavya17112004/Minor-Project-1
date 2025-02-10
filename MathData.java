import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
// Program extracts all math formula from NTCIR Documents.
public class MathData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File Directory=new File("NTCIR_DATA_CONTAINER/NTCIR12_MathIR_WikiCorpus_v2.1.0/MathTagArticles/wpmath0000016/Articles");
		PrintWriter pw2 = new PrintWriter(new FileOutputStream(new File("NTCIR_DATA_CONTAINER/NTCIR12_MathIR_WikiCorpus_v2.1.0/TSDResult.txt"),true));
		File[] folder=Directory.listFiles();
		 long lStartTime = System.nanoTime();
		 int count=0;
		for (File f:folder)
		{				count++;
		  		  		System.out.println("Processing: "+count);
			    	   PrintWriter pw = new PrintWriter(new FileOutputStream(new File("NTCIR_DATA_CONTAINER/NTCIR12_MathIR_WikiCorpus_v2.1.0/Corpus1/"+f.getName()),true));
			    	   BufferedReader br=new BufferedReader(new FileReader(new File(f.getAbsolutePath())));
			    	   String line="";
			    	   String text="";
			    	   String content1 = " ";
				  /*
				  pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
					"<head>\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=UTF-8\"/></head><body>");*/
			    	   while((line=br.readLine())!=null)
			    	   {									
			    		   text+=line;
			    	   }			
			    	   String open1 = "<math";
			    	   String inside1 = ".*?";
			    	   String close1 = "</math>";	
			    	   String regex1 = open1 + inside1 + close1;
			    	   Matcher matcher1 = Pattern.compile(regex1, Pattern.DOTALL).matcher(text);					
			    	   while (matcher1.find()) {
			    		   content1 = matcher1.group().trim();
			    		   pw.println(content1);
			    	   }
				  //pw.println("</body>\n" + 
					//"</html>");
			    	   br.close();
			    	   pw.flush();
			    	   pw.close();
			     }
		long lEndTime = System.nanoTime();
		long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);
        pw2.println(output);
	}
}