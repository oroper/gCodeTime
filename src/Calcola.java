import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Calcola {

	int next;
	double dx=0.0, dy=0.0, dz=0.0;
	double px=0.0, py=0.0, pz=0.0;
	
	public Calcola(GCodeTime frame, File file) throws IOException {
		int ore, minuti;
		double x = 0.0;
		double y = 0.0;
		double z = 200;
		double f = 1;
		double tmp;
		dx=0.0; dy=0.0; dz=0.0;
		px=0.0; py=0.0; pz=0.0;
		double time = 0.0;
		
		FileReader fn = new FileReader(file);
		Character c;
		
		while ((next = fn.read()) > 0) {
			
			c = (char)next;

			//System.out.println(time);
			
			if (c.equals('G') ) {
				
				next = fn.read();
				c = (char)next;
				
				if ( c.equals('1') ){
					
					fn.read();
					
					next = fn.read();
					c = (char)next;
					
					if (c.equals('X')){
						
						tmp = readtok(fn);
						
						// System.out.printf("la X va a: %f\n", tmp);
						
						px += dx = absf( x - tmp );
						
						x = tmp;
						
						fn.read();
							
						tmp = readtok(fn);
						
						// System.out.printf("la Y va a: %f\n", tmp);
						
						py += dy = absf( y - tmp );
						
						y = tmp;
						
						// leggere fin che non trova una F o un andata a capo
						
						next = fn.read();
						c = (char)next;
						
						while( c != 'F' && c != '\n' ){
							next = fn.read();
							c = (char)next;
						}
							
						if( c == 'F' ){
							f = readtok(fn);
							
							//System.out.println(f);
							
							// tempo in minuti
							time += (Math.sqrt(dx*dx + dy*dy)) / f;
							time += 0.1;
						}
						else
							// tempo in minuti
							time += (Math.sqrt(dx*dx + dy*dy)) / f;
							time += 0.1;
						
					}
					
					else if(c.equals('Z')){
						tmp = readtok(fn);
						
						// System.out.printf("la z va a: %f\n", tmp);
						
						pz += dz = absf( z - tmp );
						
						z = tmp;
						
						next = fn.read();
						c = (char)next;
						
						while( c != 'F' && c != '\n' ){
							next = fn.read();
							c = (char)next;
						}
							
						if( c == 'F' ){
							f = readtok(fn);
							
							// tempo in minuti
							time += (Math.sqrt(dx*dx + dy*dy)) / f;
							time += 0.1;
						}
						else
							// tempo in minuti
							time += (Math.sqrt(dx*dx + dy*dy)) / f;
							time += 0.1;
					}
				}
			}
			
			// per eludere i problemi di lettura di G1 nei commenti
			else if(c.equals('n')){
				
				next = fn.read();
				c = (char)next;
			}
		}	
		
		fn.close();
		
		ore = ((int)(time+1))/60;
		
		minuti = ((int)(time+1))%60;
		
		
		if (ore != 0){
			frame.setMsg("\n Ci impiegherà " + ore + " ore e " + minuti + " min\n\n");
			// msg = new JLabel("\n Ci impiegherà " + ore + " ore e " + minuti + " min\n\n", JLabel.CENTER);
			//frame.add(msg, BorderLayout.CENTER);
		}
		else{
			frame.setMsg("\n Ci impiegherà " + minuti + " min\n\n");
			//msg = new JLabel("\n Ci impiegherà " + minuti + " min\n\n", JLabel.CENTER);
			//frame.add(msg, BorderLayout.CENTER);
		}
	}
	
	private Double readtok(final FileReader fd) throws IOException{
		int t;
		Character h;
		String str = "";
		
		while( (t = fd.read() )> 0) {
			h=(char)t;
			// Se a capo, termine del file o spazio
			if( h.equals('\n') || h.equals('\0') || h.equals(' ')){
				// non inserire il carattere letto, stringa già terminata all'iterazione precedente
				break;
			}
			// Inserisce cararrete nella stringa
			str = str + h;
		}
		
		return Double.parseDouble(str);
	}

	private double absf(double x){
		int t;
		
		t = (int)x;
		
		if(t<0)
			return -x;
		
		return x;
	}

}