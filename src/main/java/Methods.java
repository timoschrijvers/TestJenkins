import java.util.Comparator;
import java.util.List;

public class Methods {
    Database database;
    Server server;

    public Methods() {
        database = new Database();
        server = new Server();
    }

    public Methods(Database database, Server server) {
        this.database = database;
        this.server = server;
    }

    public void sorteerOplopend(List<Integer> ints){
        ints.sort(Comparator.naturalOrder());
    }

    public void SorteerAflopend(List<Integer> ints){
        ints.sort(Comparator.reverseOrder());
    }

    public String getUppercaseFromDatabase(int i){
        return database.get(i).toUpperCase();
    }
    public int getFromServer(){
        return server.doSomething()*10;
    }
}
