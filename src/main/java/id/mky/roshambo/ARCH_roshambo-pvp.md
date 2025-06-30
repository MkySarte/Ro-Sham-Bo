## Ziel:
    
    - erstmal rein über console
    
    
    - Saubere trennung der einzelnen Klasssen
        - packages erstellen MVC + Network + Util
        - klassen erstellen (Main, Roshambo, StartController)
        - was können oder sollen die klassen machen 
    


### model/
    
    class Player:
            String name;
            int wins;

    warum enum weil die spiel logik später im server sein wird 
    (p1 == SCHERE && p2 == PAPIER ) return GEWONNEN
    animation gewonnen wird dann ausgeführt
    
    enum Move:
            SCHERE,
            STEIN,
            PAPIER

    enum  Gameresult
            GEWONNEN
            VERLOREN
            UNENTSCHIEDEN

### network/

    class Networkmessage
            String spielername
            Move mv
            Gameresult ergebnis
            String animation

#### wofür ist die kalsse:

kommunikation zwischen Client und Server mit .json

    class GameClient
            Socket 
            Printwrite
            ClientListener
            
            connect()
            sendMove()
            disconnect()
            setMessageHandler()
#### wofür ist die kalsse:

Es stellt eine verbindung mit Roshambo-Server her und startet den ClientListener

    class ClientListener implements Runnable
    
            BufferedReader reader
            MessageHandler handler

            run()
#### wofür ist die kalsse:
Es wartet auf ein signal vom server -> wer gewonnen hat ..
#### warum eigene thread ?
weil es parallel zu dem spiel laufen soll(main) 
Spiel wartet ohne das es blockiert wird 

    interface MessageHandler
            void handleMessage(NetworkMessage message)

#### wofür ist die kalsse:
Bekommt nachrichten von dem Roshambo-Server und reagiert darauf

util/

    class JsonSerialize
            String toJson(Object obj)


#### wofür ist die kalsse:
Wandelt JSON in ein beliebiges Java-Objekt um und zurück

controller/

    class PvPGameController
            GameClient client
            handleMessage(NetworkMessage message)
            sendMove(Move move)
            Gui-refresh == Gameresult