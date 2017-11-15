import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class e4HR {

	static //hadeelo el start wl end w howa yedeeny 3adad el seconds el mabenhom hmm 
	ArrayList<Double> data;
	static int difference = 8;
	static int baseline; 
	static int maxAtBaseline;

	
	private static void loadData(String path, int skip) throws IOException {
		data= new ArrayList<Double>();
		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
//		int skip = 3;
		while ((currentLine = br.readLine()) != null) {
			//ATTENTION: zabat el file sheel awel 3 rows
			if(skip==0){
			data.add(Double.parseDouble(currentLine));
//					Integer.parseInt(currentLine));
			//loads el hr fel arraylist
			}
			else {
				skip--;
			}
			}
		}

	public static long getDiff(String time1, String time2) throws ParseException{
	
//		String time1 = "16:00:00";
//		String time2 = "19:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime dateTime1= LocalDateTime.parse(time1, formatter);
		LocalDateTime dateTime2= LocalDateTime.parse(time2, formatter);

		long diffInSeconds = java.time.Duration.between(dateTime2, dateTime1).getSeconds();
	
		return diffInSeconds;
	}
	
	public static double baseline(long start, int frequency){
		double returned = 0.0;
		double average = 0.0; 
		long end = 10*60*frequency + start;
		int totalReadings = 10*60*frequency; 
		for(int i=(int) start; i<end; i++){
		average+=data.get(i);
		
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		returned = average/totalReadings;	
		return returned;
		//3ala total readings 3hsna law readings msh mazboot yeb2a mabawaztesh el average.
	}

	
	
	public static double think(long start, int frequency){
		double returned = 0.0;
		double average = 0.0; 
		long end = 5*60*frequency + start;
		int totalReadings = 5*60*frequency; 
		
		for(int i=(int) start; i<end; i++){
		average+=data.get(i);
		
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		returned = average/totalReadings;		
		return returned;
	}

	public static double rest(long start, int frequency){
		double returned = 0.0;
		double average = 0.0; 
		long end = 10*60*frequency + start;
		int totalReadings = 10*60*frequency; 
		for(int i=(int)start; i<end; i++){
		average+=data.get(i);
		
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		returned = average/totalReadings;		
		return returned;
	
	}
	
	public static double thinktwo(long start, int frequency){
		double returned = 0.0;
		double average = 0.0; 
		long end = 5*60*frequency + start;
		int totalReadings = 5*60*frequency; 
		for(int i=(int) start; i<end; i++){
		average+=data.get(i);
		
		if(data.get(i)==0){
			totalReadings--;
		}
		}
	
		returned = average/totalReadings;		
		return returned;		
	}

	
	public static void filter(){
		
		Double previousValue = data.get(0);
		for(int i=1; i<data.size(); i++){
			Double current = data.get(i);
			if(current == 0){
				break;
			}else {
				if(current - previousValue >difference){
					current =(double) 0;
				}
				else {
				previousValue = data.get(i);	
				}
				  }
			}
	}

	
	public static void main(String[] args) throws ParseException, IOException {
		
		
		//3andy csv 3ayza adeelo awel el file w awel el mafroud abda2 yeraga3ly 3adad secs mo3ayan habda2 akhod readings 
		//mn 3and el sec dy ba2a
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String date = "2014-11-25 ";
		
		int repeat = Integer.parseInt(br.readLine());
		
		while(repeat--!=-1){
		
//		System.out.println(\);
			System.out.println("number?");
//		System.out.println();
		String num = br.readLine(); 
		String hr = "HR"+ num + ".csv"; 
		String eda = "EDA"+num+ ".csv"; 
		String temp = "TEMP"+num+ ".csv"; 
		System.out.println("e4 start: ");
		String e4start = date + br.readLine();
		
		System.out.println("h6 start: ");
		String h6start = date + br.readLine();
		
		System.out.println("think start: ");
		String thinkStart= date + br.readLine(); 
		
		System.out.println("rest start: ");
		String restStart = date + br.readLine();
		
		System.out.println("stress start: ");
		String overStart = date +  br.readLine();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		loadData(hr, 3);
		filter();
		long start;
		double base; 
		int frequency = 1;
		
		start = getDiff(h6start, e4start);
		base = baseline(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(thinkStart, e4start);
		base = think(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(restStart, e4start);
		base = rest(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(overStart, e4start);
		base = thinktwo(start, frequency);
		System.out.println(base);
System.out.println();
		System.out.println("---");	
		
//		System.out.println("EDA");
		
		loadData(eda, 4);
		filter();
		frequency = 4;
		
		start = getDiff(h6start, e4start);
		base = baseline(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(thinkStart, e4start);
		base = think(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(restStart, e4start);
		base = rest(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(overStart, e4start);
		base = thinktwo(start, frequency);
		System.out.println(base);
System.out.println();
		System.out.println("---");	
		
//		System.out.println("TEMP");
		
		loadData(temp, 6);
		filter();
		frequency = 4;
//		syso
		start = getDiff(h6start, e4start);
		base = baseline(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(thinkStart, e4start);
		base = think(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(restStart, e4start);
		base = rest(start, frequency);
		System.out.println(base);
System.out.println();
		start = getDiff(overStart, e4start);
		base = thinktwo(start, frequency);
		System.out.println(base);
System.out.println();
		
	}
//		"2014-11-25 16:00:00"

		
	}
	
	
}
