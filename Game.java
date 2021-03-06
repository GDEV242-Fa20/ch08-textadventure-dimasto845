
import java.util.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Scanner reader;
    private Player player;
    private Room currentRoom;   
    private Room prevRoom;
    private Item item;
    private Room roomStack[];
    private int top;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
        reader = new Scanner(System.in);
        roomStack = new Room[500];
        top=-1;
    }
    private void createPlayer() {
        System.out.println("Enter player name: ");
        String name = reader.nextLine();
        player.setPlayerName(name);
        createRooms();
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Hello " + player.getPlayerName() + ", ");
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        createPlayer();
        System.out.println("Hello player " + player.getPlayerName() + "!\n");
        System.out.println(player.getPlayerDescription());
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar;
        
        Item outsideItems[] = { new Item("Nothing", 0),
                               new Item("Plants",200),
                               new Item("Benches",1500) };
        Item theaterItems[] = { new Item("Projector", 2000),
                               new Item("Chairs",5000),
                               new Item("Table",200) };
        Item pubItem[] = { new Item("Soft Drinks", 300),
                               new Item("Plants",500) };
        Item computerItem[] = { new Item("Computers", 1200),
                               new Item("Chairs",2000) };
        Item officeItem[] = { new Item("Tele[hone", 100),
                               new Item("Table",200),
                               new Item("Chairs",500) };
                               

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");
        
        outside = addItemsToRoom(outside, outsideItems);
        theater = addItemsToRoom(theater, theaterItems);
        pub = addItemsToRoom(pub, pubItem);
        lab = addItemsToRoom(lab, computerItem);
        office = addItemsToRoom(office, officeItem);
        
        
        
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        office.setExit("down", cellar);


        
        //currentRoom = outside;  // start game outside
        currentRoom = outside;
        
        
        prevRoom = null;
        

    }
    
    
    
    
    // starter file

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println("   go quit help");
    }
    
    
    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
           push(currentRoom);
           currentRoom = nextRoom;
           System.out.println(currentRoom.getDescription());
        }
    }
    
    
    
    
    private Room addItemsToRoom(Room room, Item items[]) 
    {
    for (int i=0; i < items.length; i++) 
    {
        room.addItem(items[i]);
    }
    return room;
    }


    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        createPlayer();
        
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if(commandWord.equals("eat"))
            System.out.println("You have eaten now and you are not hungry any more");
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("back"))
            backRoom();
        else if (commandWord.equals("take"))
            pickUpItemFromRoom(command);
        else if (commandWord.equals("drop"))
            dropItemInHand(command);
        else if (commandWord.equals("items"))
            player.printItem();
        else if (commandWord.equals("eatcookie"))
            player.eatCookie();
            


        return wantToQuit;
    }
    
    private void pickUpItemFromRoom(Command command)
    {
        if(!command.hasSecondWord()){
        
            System.out.println("There is no item specified");
            return;
        }
        String itemName = command.getSecondWord();
        player.pickUpItem(itemName);
    }
    
    private void dropItemInHand(Command command)
    {
        if(!command.hasSecondWord()){
        
            System.out.println("There is no item specified");
            return;
        }
        String itemName = command.getSecondWord();
        player.dropItem(itemName);
    }

    

    
    
    private void backRoom(){
    player.movePlayerToPreviousRoom();
     
    }
    
    private void push(Room room) {
    if(top == roomStack.length-1)
    System.out.println("Room stack is full");
    else 
    roomStack[++top]= room;
    }
    
    private Room pop()
    {
        if(top < 0)
        {
            System.out.println("Sorry, you are outside the bunglow and there is no previous room to go");
            return null;
        }
        else
        return roomStack[top--];
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    public void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
        
        System.out.println();
    }
}
