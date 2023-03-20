package train;

import java.util.*;

class Station {
  private String name;
  private LinkedList<Station> connections;

  public Station(String name) {
    this.name = name;
    this.connections = new LinkedList<>();
  }

  public void addConnection(Station station) {
	 if(!connections.contains(station)) {
    connections.add(station);
	 }
  }  
  public String getName() {
    return name;
  }

  public LinkedList<Station> getConnections() {
    return connections;
  }

  @Override
  public String toString() {
    return name;
  }
}

class TransportationNetwork {
  private Map<String, Station> stations;

  public TransportationNetwork() {
    stations = new HashMap<>();
  }

  public void addStation(String name) {
    stations.put(name, new Station(name));
  }

  public void addConnection(String station1, String station2) {
    Station s1 = stations.get(station1);
    Station s2 = stations.get(station2);
    s1.addConnection(s2);
    s2.addConnection(s1);
  }

  public Station getStation(String name) {
    return stations.get(name);
  }

  public void printConnections() {
    for (Map.Entry<String, Station> entry : stations.entrySet()) {
      System.out.println("Station: " + entry.getKey());
      System.out.println("Connections: " + entry.getValue().getConnections());
      System.out.println();
    }
  }

  public List<Station> getShortestPath(String start, String end) {
    Map<Station, Station> previous = new HashMap<>();
    Map<Station, Integer> distance = new HashMap<>();
    Station startStation = stations.get(start);
    Station endStation = stations.get(end);
    LinkedList<Station> queue = new LinkedList<>();
    Set<Station> visited = new HashSet<>();
    for (Map.Entry<String, Station> entry : stations.entrySet()) {
      distance.put(entry.getValue(), Integer.MAX_VALUE);
    }
    distance.put(startStation, 0);
    queue.add(startStation);

    while (!queue.isEmpty()) {
      Station current = queue.poll();
      if(current.equals(endStation)) {
    	  return reconstructPath(previous, endStation);
      }
      for (Station neighbor : current.getConnections()) {
    	    if (visited.contains(neighbor)) {
    	      continue;
    	    }
    	    int tentativeDistance = distance.get(current) + 1;

    	    if (tentativeDistance < distance.get(neighbor)) {
    	      distance.put(neighbor, tentativeDistance);
    	      previous.put(neighbor, current);
    	      queue.add(neighbor);
    	    }
    	  }
    	  visited.add(current);
    	}
    	return null;
    	}
  
  private List<Station> reconstructPath(Map<Station, Station> previous, Station current) {
	  LinkedList<Station> path = new LinkedList<>();
	  path.add(current);
	  while (previous.containsKey(current)) {
	  current = previous.get(current);
	  path.addFirst(current);
	  }
	  return path;
	  }
}

	public class Train {
		public static void main(String[] args) {
			test();
			//you can see how its work by small testcase by uncommending below function
			//simpleTest();
		}
		  public static void simpleTest() {
			  String[] station= {"a","b","c","d","e","f"};
			  TransportationNetwork network = new TransportationNetwork();
			  for(int i=0;i<station.length;i++) {
					network.addStation(station[i]);
					}
			  network.addConnection("a", "b");
			  network.addConnection("b", "c");
			  network.addConnection("c", "d");
			  network.addConnection("e", "f");
			  network.addConnection("c", "f");
			  network.printConnections();
			  List<Station> path = network.getShortestPath("a", "f");//A----B----C----F
			  System.out.println("Shortest Path: " + path);
			  
		  }
		  public static void test() {
			    String[] tnStations = {"Erode Junction", "Walajah Road Junction", "Kizhvelur", "Virudunagar Junction", "Tiruchendur", "Karaikkal", "Chennai Egmore", "Virudhachalam", "Villupuram", "Manamadurai", "Tirupathur Padi", "Dindigul Junction", "Dharmapuri", "Mayiladuthurai", "Jolarpettai Junction", "Sivakasi", "Pattabiram", "Chennai Central", "Kumbakonam Town", "Tindivanam", "Nagapattinam", "Vridhachalam Town", "Tiruppattur Junction", "Tiruvennainallur", "Karaikkudi Junction", "Ramanathapuram", "Kolathur", "Tiruttangal", "Tiruchendurai", "Karaikal", "Tirupuram", "Tirumangalam", "Ponneri", "Nagari", "Sirkali", "Tirupattur Junction", "Rameswaram", "Tiruvangur", "Panruti", "Villivakkam", "Arakkonam", "Tiruppur", "Tambaram", "Tirupathur", "Valadi", "Tiruttani", "Tirunagesvaram", "Kodaikanal Road", "Salem Junction", "Chennai Beach", "Thanjavur Junction", "Chengam", "Tiruverumbur", "Katpadi", "Palakkad Junction", "Pallavanthangal", "Nagercoil Junction", "Karur Junction", "Cholapuram", "Tiruvallur", "Kovilpatti", "Thakkolam", "Alwar Tirunagari", "Krishnankovil", "Tiruppuvanam", "Sankarankovil", "Ambur", "Cuddalore", "Ottivakkam", "Vellanguli", "Pondicherry", "Vanchi Maniyachchi Junction", "Tirunelveli Junction", "Tiruvidaimarudur", "Tirupunavasal", "Ariyalur", "Dharmapuri Town", "Tiruchengode", "Tiruvadi", "Tiruvannamalai", "Udumalaipettai", "Tiruvarur Junction", "Tuticorin", "Katpadi Junction", "Vaniyambadi", "Tirumayam", "Kuzhithurai", "Tiruppattur", "Tiruttangadi", "Pugalur", "Tiruppachetti", "Sirkazhi", "Mannargudi", "Chengalpattu Junction", "Valparai", "Tiruchchirappalli Junction", "Neyveli", "Arakkonam Junction", "Perambur", "Sulur Road", "Nagapattinam Junction", "Tirunavalur", "Kumbakonam", "Tirupati", "Villupuram Junction", "Sengottai", "Tiruvarur", "Jolarpet", "Chidambaram Junction", "Tiruvannamalai Junction", "Coimbatore Junction", "Kanniyakumari", "Mannargudi Junction", "Kallakudi", "Nagore", "Pudukkottai", "Kanyakumari", "Pothanur", "Madurai Junction", "Tirukkovilur", "Renigunta Junction", "Mayanoor", "Cuddalore Port Junction", "Chinnamanur", "Kallakurichi", "Thiruchendur", "Vriddhachalam", "Sankaridurg", "Sirkazhi Junction", "Tiruvottiyur", "Tiruchirapalli Fort", "Paramakudi", "Tenkasi Junction", "Pattukkottai", "Gummidipoondi", "Chidambaram", "Vellore Cantonment", "Papanasam", "Avadi", "Mayiladuturai Junction", "Gudur Junction", "Mettur"};
				TransportationNetwork network = new TransportationNetwork();
				for(int i=0;i<tnStations.length;i++) {
				network.addStation(tnStations[i]);
				}
				String[] Rameswaram= {"Mandapam", "Ramanathapuram", "Manamadurai", "Paramakudi", "Pamban Junction"};
				assist("Rameswaram", Rameswaram, network, tnStations);
				String[] Sirkali= {"Chidambaram", "Mayiladuturai Junction", "Nagapattinam Junction", "Tirupathiripuliyur", "Vaitheeswarankovil", "Vaithisvarankoil"};
				assist("Sirkali", Sirkali, network, tnStations);
				String[] Nagari= {"Renigunta Junction", "Gudur Junction", "Katpadi Junction"};
				assist("Nagari", Nagari, network, tnStations);
				String[] Tirumangalam= {"Madurai Junction","Kodaikanal Road Junction"};
				assist("Tirumangalam", Tirumangalam, network, tnStations);
				String[] Karaikal= {"Chennai Egmore", "Tiruchirapalli Junction", "Nagapattinam Junction", "Thanjavur Junction", "Villupuram Junction", "Thiruvarur Junction", "Mayiladuturai Junction", "Cuddalore Port Junction", "Chidambaram Junction", "Tirupati", "Kumbakonam", "Mannargudi Junction", "Peralam Junction", "Tiruvarur Junction", "Kumbakonam Town", "Sirkazhi Junction", "Puducherry", "Velankanni", "Sengottai","Tirupuram"};
				assist("Karaikal", Karaikal, network, tnStations);
				String[] Tiruttangal = {"Virudunagar Junction", "Sivakasi", "Rajapalayam", "Kovilpatti", "Tuticorin", "Tirunelveli Junction", "Tenkasi Junction",""};
				assist("Tiruttangal", Tiruttangal, network, tnStations);
				String[] Ramanathapuram= {"Paramakudi","Rameswaram","Kolathur"};
				assist("Ramanathapuram", Ramanathapuram, network, tnStations);
				String[] Karaikkudi = {"Tiruchirapalli Junction", "Manamadurai Junction", "Pudukkottai", "Pattukkottai", "Rameswaram", "Thanjavur Junction", "Chettinad", "Devakottai Road", "Tiruvarur Junction"};
				assist("Karaikkudi Junction", Karaikkudi, network, tnStations);
				String[] Tiruppattur = {"Jolarpettai Junction", "Salem Junction", "Katpadi Junction", "Erode Junction", "Coimbatore Junction", "Chennai Central"};
				assist("Tiruppattur Junction", Tiruppattur, network, tnStations);
				String[] Vridhachalam= {"Tiruchirapalli Junction", "Mayiladuthurai Junction", "Villupuram Junction", "Chidambaram"};
				assist("Vridhachalam Town", Vridhachalam, network, tnStations);
				String[] Tindivanam= {"Chennai Egmore", "Villupuram Junction", "Cuddalore Port Junction", "Puducherry (Pondicherry)", "Vriddhachalam Junction", "Tiruchirappalli Junction", "Thanjavur Junction", "Karaikal Junction", "Nagore"};
				assist("Tindivanam", Tindivanam, network, tnStations);
	            String[] Kumbakonam= {"Thanjavur", "Mayiladuturai", "Tiruchirapalli Junction", "Chennai Egmore", "Villupuram Junction", "Cuddalore Port Junction", "Tirupadiripuliyur", "Chidambaram"};
				assist("Kumbakonam Town",Kumbakonam,network,tnStations);
				String[] Chennai1= {"Ponneri","Chennai Egmore", "Pattabiram", "Tiruvallur", "Arakkonam", "Katpadi", "Gudur", "Renigunta", "Vijayawada Junction", "Visakhapatnam Junction", "Coimbatore Junction", "Madurai Junction", "Tiruchchirappalli Junction", "Tirupati", "Thiruvananthapuram"};
				assist("Chennai Central",Chennai1,network,tnStations);
				String[] Pattabiram= {"Chennai Central", "Chennai Beach", "Arakkonam Junction", "Tiruvallur", "Avadi", "Tiruninravur", "Thirumullaivoyal", "Ambattur", "Korattur", "Pattabiram Military Siding", "Pattabiram E Depot", "Tiruvallur", "Avadi"};
				assist("Pattabiram",Pattabiram,network,tnStations);
				String[] Sivakasi= {"Kovilpatti", "Tirunelveli Junction", "Madurai Junction", "Villupuram Junction", "Tuticorin", "Virudunagar Junction", "Tiruchirapalli Junction", "Tiruppur", "Chidambaram Junction", "Arakkonam Junction", "Tiruvallur", "Katpadi Junction", "Villupuram", "Tirupattur Junction", "Nellore", "Sankarankovil", "Vridhachalam Town","Virudhachalam", "Tiruvarur Junction", "Puducherry",  "Cholapuram", "Sivakasi", "Kottayam",  "Tenkasi Junction", "Aruppukkottai", "Tiruppuvanam", "Tiruttangal", "Tirupachetti", "Tiruttani", "Aluva", "Tirupathur", "Tiruvennainallur", "Shoranur Junction",  "Valliyur", "Ottivakkam", "Kayankulam Junction", "Tirumangalam", "Sirkali", "Kumbakonam", "Tiruvallikeni", "Vriddhachalam", "Mayanoor", "Sankaridurg", "Tiruvidaimarudur", "Tiruchendurai", "Tirupunavasal", "Tirupattur", "Tiruvangur", "Tiruvannamalai", "Nagari", "Sirkazhi", "Tiruppur South"};
				assist("Sivakasi",Sivakasi,network,tnStations);
				String[] Jolarpettai= {"Chennai Central", "Coimbatore Junction", "Kacheguda", "Kanyakumari", "Karur Junction", "Katpadi Junction", "Madurai Junction", "Salem Junction", "Tiruchirappalli Junction", "Tirupati", "Vellore Cantonment", "Villupuram Junction", "Yesvantpur Junction"};
				assist("Jolarpettai Junction",Jolarpettai,network,tnStations);
				String[] Mayiladuthurai= {"Kumbakonam", "Sirkazhi", "Tiruvarur Junction", "Nagore", "Thanjavur Junction", "Tiruchchirappalli Junction", "Chidambaram Junction", "Villupuram Junction", "Tirupanandal", "Thiruvarur Junction", "Peralam Junction", "Nagappattinam Junction", "Chennai Egmore", "Mannargudi Junction"};
				assist("Mayiladuthurai",Mayiladuthurai,network,tnStations);
				String[] Dharmapuri= {"Salem Junction", "Bangalore City Junction", "Hosur"};
				assist("Dharmapuri",Dharmapuri,network,tnStations);
	            String[] Dindigul = {"Chennai Egmore", "Madurai Junction", "Coimbatore Junction", "Trichy Junction", "Palakkad Junction", "Thiruvananthapuram Central", "Kollam Junction", "Tiruchirappalli Junction", "Tiruppur", "Karur Junction", "Erode Junction", "Salem Junction", "Virudhunagar Junction", "Rameswaram", "Kanyakumari"};
	            assist("Dindigul Junction",Dindigul,network,tnStations);
	            String[] Tirupathur = {"Chennai Egmore", "Tiruvallur", "Arakkonam Junction", "Katpadi", "Jolarpettai Junction", "Vaniyambadi", "Gudiyattam", "Ambur", "Vellore Cantonment", "Tirupattur Junction"};
	            assist("Tirupathur Padi",Tirupathur,network,tnStations);
	            String[] Manamadurai= {"Chennai Egmore", "Kumbakonam Town", "Tiruchchirappalli Junction", "Rameswaram", "Tiruppur", "Karaikkudi Junction", "Tirunelveli Junction", "Sengottai", "Tiruchendur", "Virudunagar Junction"};
				assist("Manamadurai",Manamadurai,network,tnStations);
	            String[] Villupuram= {"Chennai Egmore", "Vridhachalam Junction", "Villupuram Junction", "Tiruchirapalli Junction", "Tirunelveli Junction", "Tirupati", "Mayiladuthurai Junction", "Tiruchendur", "Tirupur", "Mannargudi Junction", "Katpadi Junction", "Cuddalore Port Junction", "Jolarpettai Junction", "Arakkonam Junction", "Vellore Cantonment", "Tirupattur Junction", "Salem Junction", "Tiruvannamalai Junction", "Tambaram", "Chengalpattu Junction", "Chennai Beach", "Pondicherry", "Karaikkal", "Nagore Junction", "Tiruchirapalli Fort Station", "Thanjavur Junction", "Kumbakonam", "Mayiladuturai Junction", "Sirkazhi Junction", "Chidambaram Junction", "Cuddalore Junction", "Puducherry"}; 
	            assist("Villupuram",Villupuram,network,tnStations);
				String[] Virudhachalam = {"Chennai Central", "Chennai Egmore", "Mayiladuthurai", "Salem Junction", "Tiruchirappalli Junction", "Tirupati", "Villupuram Junction", "Thanjavur Junction", "Vriddhachalam", "Kumbakonam Junction", "Nagapattinam Junction", "Thiruvarur Junction", "Sirkazhi Junction", "Vanchi Maniyachchi Junction", "Tiruchendur Junction", "Velankanni", "Pondicherry", "Ernakulam Junction", "Madurai Junction", "Mangalore Central", "Palakkad Junction", "Rameswaram", "Tirunelveli Junction", "Tiruppur", "Coimbatore Junction", "Karaikal", "Kanyakumari", "Tenkasi Junction", "Virudhunagar Junction"};
				assist("Virudhachalam",Virudhachalam,network,tnStations);
				String[] Karaikkal= {"Chennai Egmore","Nagapattinam Junction"};
				assist("Karaikkal",Karaikkal,network,tnStations);
				String[] Erode= {"Tiruppur", "Salem Junction", "Karur Junction", "Tiruchirappalli Junction", "Jolarpettai Junction", "Coimbatore Junction", "Katpadi Junction"};
				assist("Erode Junction",Erode,network,tnStations);
			    String[] Walajah= {"Arakkonam Junction", "Katpadi Junction", "Chittoor", "Renigunta Junction"};
			    assist("Walajah Road Junction",Walajah,network,tnStations);
			    String[] Kizhvelur = {"Nagercoil Junction","Tirunelveli Junction","Valliyur"};
			    assist("Kizhvelur",Kizhvelur,network,tnStations);
			    String [] Virudunagar = {"Sivakasi", "Rajapalayam", "Aruppukkottai", "Tiruchendur", "Madurai Junction", "Tirunelveli Junction", "Kovilpatti", "Vanchi Maniyachchi Junction", "Tuticorin", "Tenkasi Junction"};
			    assist("Virudunagar Junction",Virudunagar,network,tnStations);
			    String[] Tiruchendur = {"Tirunelveli Junction", "Kovilpatti", "Vanchi Maniyachchi Junction", "Kayalpattinam", "Tuticorin", "Valliyur", "Nazareth", "Nanguneri", "Kadambur", "Srivaikuntam"};
			    assist("Tiruchendur",Tiruchendur,network,tnStations);
			    String[] Chennai= {"Pattabiram", "Tambaram", "Chengalpattu Junction", "Villupuram Junction", "Kumbakonam Town", "Mayiladuthurai", "Nagapattinam", "Thanjavur Junction", "Tiruchchirappalli Junction", "Pudukkottai", "Karaikudi Junction", "Sivaganga", "Manamadurai", "Ramanathapuram", "Paramakkudi", "Tirunelveli Junction", "Kovilpatti", "Sattur Junction", "Virudhunagar Junction", "Madurai Junction", "Dindigul Junction", "Karur Junction", "Salem Junction", "Erode Junction", "Tiruppur", "Coimbatore Junction", "Palakkad Junction", "Thrissur", "Ernakulam Junction", "Kollam Junction", "Thiruvananthapuram Central"};
			    assist("Chennai Egmore",Chennai,network,tnStations);
			    network.printConnections();

				List<Station> path = network.getShortestPath("Cuddalore Port Junction", "Arakkonam");
				System.out.println("Shortest Path: " + path);
		  }
		  public static void assist(String a,String[] b,TransportationNetwork c,String[]d) {
			  for(int i=0;i<b.length;i++) {
			    	if(Arrays.asList(d).contains(b[i])) {
			    	c.addConnection(a, b[i]);
			    	}
			    }
		  }
	}