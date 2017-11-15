import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class h6hr {
	static ArrayList<Integer> data; 
	static int baseline; 
	static int difference = 6;
	static int maxAtBaseline;
	static int normal;
	static int littleStress; 
	static int stress; 
	static int greatStress;
	
	private static void loadData(String path) throws IOException {
	data= new ArrayList<Integer>();
	String currentLine = "";
	FileReader fileReader = new FileReader(path);
	BufferedReader br = new BufferedReader(fileReader);
	int skip = 3;
	while ((currentLine = br.readLine()) != null) {
		//ATTENTION: zabat el file sheel awel 3 rows
		if(skip==0){
		String[] loadedData = currentLine.split(",");
		if(loadedData.length==2)
		data.add(0);
		//3shan howa empty wl inteegr fel empty haybooz
		else 
		data.add(Integer.parseInt(loadedData[2]));
		//loads el hr fel arraylist
		}
		else {
			skip--;
		}
		}
	}

	
	public static int baseline(){
		int average = 0; 
		maxAtBaseline = data.get(0);
		int totalReadings = 900; 
		for(int i=0; i<900; i++){
		average+=data.get(i);
		if(data.get(i)>maxAtBaseline)
			maxAtBaseline=data.get(i);
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		baseline = average/totalReadings;
		normal=baseline+18;
		littleStress = normal + 18; 
		stress = littleStress + 18; 
		greatStress = stress + 18;
		
		return baseline;
		
		//3ala total readings 3hsna law readings msh mazboot yeb2a mabawaztesh el average.
	}


	public static int think(){
		int average = 0; 
		maxAtBaseline = data.get(900);
		int totalReadings = 300; 
		for(int i=900; i<1200; i++){
		average+=data.get(i);
		if(data.get(i)>maxAtBaseline)
			maxAtBaseline=data.get(i);
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		baseline = average/totalReadings;
		return baseline;
		
	}

	public static int rest(){
		int average = 0; 
		maxAtBaseline = data.get(1200);
		int totalReadings = 300; 
		for(int i=1200; i<1500; i++){
		average+=data.get(i);
		if(data.get(i)>maxAtBaseline)
			maxAtBaseline=data.get(i);
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		baseline = average/totalReadings;
		return baseline;
		
	}
	
	public static int thinktwo(){
		int average = 0; 
		maxAtBaseline = data.get(1500);
		int maxxx=data.size();
		int totalReadings = maxxx-1500; 
		for(int i=1500; i<maxxx; i++){
		average+=data.get(i);
		if(data.get(i)>maxAtBaseline)
			maxAtBaseline=data.get(i);
		if(data.get(i)==0){
			totalReadings--;
		}
		}
		baseline = average/totalReadings;
		return baseline;
		
	}

	public static void filter(){
	
		int previousValue = data.get(0);
		for(int i=1; i<data.size(); i++){
			int current = data.get(i);
			if(current == 0){
				break;
			}else {
				if(current - previousValue >difference){
					current =0;
				}
				else {
				previousValue = data.get(i);	
				}
				  }
			}
	}
	
	public static Hashtable<Integer,Integer> thinking(int start, int end){
		//92,110,128,146,165
		//baseline till +18 = normal
		//baseline + 36 = little stress 
		//baseline + 44 = stress 
		//baseline + 62 = great stress 
	

		//nodata =0
		//normal =1
		//littlestress = 2
		//stress =3
		//greatstress=4
		
		Hashtable<Integer, Integer> values = new Hashtable<Integer, Integer>();
		for (int i = start; i < end; i++) {
			int current = data.get(i);
			if(current==0)
				values.put(i,0);
			else if (current-baseline <= 18)
				values.put(i,1);
			else if(current-baseline <= 36)
				values.put(i, 2);
			else if(current-baseline <= 54)
				values.put(i, 3);
			else values.put(i, 4);
		}
		return values;
	} 
	
	public static ArrayList<String> analyze(Hashtable<Integer, Integer> dataValues, int start, int end, int ta2seem){
		
		ArrayList<String> toBeReturned = new ArrayList<String>();
		
		int loop = dataValues.size()/ta2seem;
		for (int i = 0; i < loop; i++) {
			int x [] = new int [5];
			for (int j = 0; j < 30; j++) {
				int s = dataValues.get(start);
				x[s]++;
				start++;
			}
		int max = 0;	
		for (int j = 0; j < x.length; j++) {
			if(x[j]>max)
				max = j;
		}
		
		switch(max){
		case 0: toBeReturned.add("no data"); break;
		case 1: toBeReturned.add("normal");  break;
		case 2: toBeReturned.add("little stress");  break;
		case 3: toBeReturned.add("stress");  break;
		case 4: toBeReturned.add("great stress");  break;
		}
		}
		return toBeReturned;
		
	}
	
	
	public static ArrayList<String> average(ArrayList<String> dataaa, int ta2seem){
		
		ArrayList<String> toBeReturned = new ArrayList<String>();
		int loop = dataaa.size()/ta2seem;
		int y=0;
		for (int i = 0; i < ta2seem; i++) {
			int x [] = new int [5];
			
			for (int j = 0; j < loop; j++) {

				switch(dataaa.get(y)){
				case "no data": x[0]++; break;
				case "normal": x[1]++;  break;
				case "little stress": x[2]++;  break;
				case "stress": x[3]++;  break;
				case "great stress":x[4]++;  break;
				}
				y++;
				}
			
				int max = -1;

//				System.out.println(x[1]);
//				System.out.println(x[3]);
				for (int jj = 0; jj < x.length; jj++) {
//					if(x[jj]==max)
//						max=jj;
					if(x[jj]>=max)
						max = jj;
//					System.out.println(max);
				}
				
				switch(max){
				case 0: toBeReturned.add("no data"); break;
				case 1: toBeReturned.add("normal");  break;
				case 2: toBeReturned.add("little stress");  break;
				case 3: toBeReturned.add("stress");  break;
				case 4: toBeReturned.add("great stress");  break;
				}	
//				y++;
			}
		
		return toBeReturned; 
	}
	
	
	public static void main(String[] args) throws IOException {

//System.out.println("alaa: ");		
		System.out.println();
		loadData("h6911.csv");
		filter();
		int baseline = baseline();
		int thinking = think();
		int rest = rest();
		int think2 = thinktwo();

		System.out.println(baseline);
		System.out.println(thinking);
		System.out.println(rest);
		System.out.println(think2);
		
	
	}
	
}
