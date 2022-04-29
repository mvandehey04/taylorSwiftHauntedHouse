import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
 * @version 2022.01.19
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    private Room TaylorSwift, FearlessTV, SpeakNow, RedTV, nineteenEightyNine, reputation, Lover, folklore, evermore, outside, end;
    private int move = 0;
    private int solved = 0;
    private boolean wearingDress = false;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        inventory = new HashMap<String, Item>();
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Room TaylorSwift, FearlessTV, SpeakNow, RedTV, nineteenEightyNine, reputation, Lover, folklore, evermore, outside, end;
      
        // create the rooms
        outside = new Room(             "outside. You made it! You're outside and have escaped the Taylor Swift" + "\n" + 
                                        "Haunted House, not to be confused with her Holiday House featured in" + "\n" +
                                        "'the last great american dynasty'. Taylor Swift hands you a chai cookie from" + "\n" +
                                        "one of her secret sessions, and sends you on your way to listen to her newest" + "\n" +
                                        "rerecordings of Red, Red (Taylor's Version).");
                                        
        TaylorSwift = new Room(         "inside of the Debut room. This room gives off major country vibes. You" + "\n" +
                                        "notice that there is an exit in four directions, but begin to wonder if" + "\n" +
                                        "there could be a trap, this is a haunted house after all. ");
                                        
        FearlessTV = new Room(          "in the Fearless (Taylor's Version) room. You spot Mr. Perfectly Fine," + "\n" +
                                        "with his arm 'round a brand-new girl in the corner of the room. A white" + "\n" +
                                        "horse is just across the room from Mr. Perfectly Fine, and you begin to" + "\n" + 
                                        "wonder who would be more trustworthy to talk to.");
        
        SpeakNow = new Room(            "in the Speak Now room. The room is filled with purple and overwhelming" 
                                        + "\n" + "sounds as fireworks shine over your sad empty town. An important letter" + "\n" + 
                                        "is sprawled out on the floor.");
        
        RedTV = new Room(               "in the Red (Taylor's Version) room. The room looks exactly as it sounds" + "\n" + 
                                        "like it would... red. John Mayer is standing in the corner, cause you know he's trouble" + "\n" +
                                        "After plaid shirt days, you notice a bottle lays on the floor, probably leftover from Taylor's" + "\n" + 
                                        "21st birthday, but it's supposed to be fun turning 21.");
        
        nineteenEightyNine = new Room(  "the 1989 room. You spot Harry Styles across the room, and he has that" + "\n" +
                                        "James Dean daydream look in his eye, his long hair, slicked back, and a white T-shirt.");
                                        
        reputation = new Room(          "the reputation room. There's glitter on the floor after the party, candle wax, " + "\n" +
                                        "and polaroid on the hardwood floor. A snake hisses at you, and you begin to wish that you" + "\n" + 
                                        "had a 'Getaway Car'.");
        
        Lover = new Room(               "in the Lover room. Wow, you quickly realize that the room you are in is a spitting image"  + "\n" +
                                        "of Cornelia St. in New York, the moon is high tongight. The chandeleier still flickers in here. The" + "\n" + 
                                        "traffic lights say 'I don't know'. But in all the maddness you spot a boarded up window that" + "\n" + 
                                        "might have a clue to your escape. Along with a set of paper rings, you like shiny things, but" + "\n" + 
                                        "find them alright enough.");
        
        folklore = new Room(            "the folklore room. It's a vast room with many things inside. Green is the color of the grass." + "\n" + 
                                        "It is almost like Centennial Park. It's like you're in a forest, with a lake off to the side," + "\n" +
                                        "where all the poets go to die. A mirrorball hangs from the ceiling and you" + "\n" +
                                        "almost seem to hear it whisper. Joe stands by himself in his yogurt shop uniform, he" + "\n" +
                                        "used to work that job at sixteen to make a little money.");
        
        evermore = new Room(            "in the evermore room. So yeah, there's a fire. It's a goshdarn blaze in the dark." + "\n" +
                                        "You see 3 doors among the flames, but third door to the west has lyrics enscribed on the door." + "\n" +
                                        "The autumn chill that wakes me up" + "\n" + "You loved the amber skies so much" + "\n" + "" + "\n" + "Maybe that's the door outside of here?");
        
        end = new Room("dead. Type 'quit' to quit the game :(");
        
        // initialise room exits
        outside.setExit(null, null);
        
        TaylorSwift.setExit("north", folklore);
        TaylorSwift.setExit("east", reputation);
        TaylorSwift.setExit("south", RedTV);
        TaylorSwift.setExit("west", FearlessTV);
        
        FearlessTV.setExit("north", evermore);
        FearlessTV.setExit("east", TaylorSwift);
        FearlessTV.setExit("south", SpeakNow);
        
        SpeakNow.setExit("north", FearlessTV);
        SpeakNow.setExit("east", RedTV);
        
        RedTV.setExit("north", TaylorSwift);
        RedTV.setExit("east", nineteenEightyNine);
        RedTV.setExit("west", SpeakNow);
        
        nineteenEightyNine.setExit("north", reputation);
        nineteenEightyNine.setExit("west", RedTV);
        
        reputation.setExit("north", Lover);
        reputation.setExit("west", TaylorSwift);
        reputation.setExit("south", nineteenEightyNine);
        
        Lover.setExit("west", folklore);
        Lover.setExit("south", reputation);
        
        folklore.setExit("east", Lover);
        folklore.setExit("south", TaylorSwift);
        folklore.setExit("west", evermore);
        
        evermore.setExit("east", folklore);
        evermore.setExit("south", FearlessTV);
        evermore.setExit("west", null);

        currentRoom = TaylorSwift;  // start game outside
        
        
        TaylorSwift.createItemsPlace("Tim_McGraw", false, "The way that your blue eyes shine, put those Georgia stars to shame at night.", true, null, null);
        
        TaylorSwift.createItemsPlace("guitar", true, "You pick up the guitar and for some reason you seem to fake a smile. After some slight examination, you notice teardrops" + 
                                                "\n" + "on the guitar, which keeps you wishing on a star.", false, nineteenEightyNine,"Harry thanks you for the guitar."
                                                + "\n");
                                                
        TaylorSwift.createItemsPlace("picture", true, "The picture shows Taylor Swift along with black sharpie over an anonymous figure.", false, evermore, "You use the flames from the room, take the 'Picture to Burn', and"
                                                                                                                                                            + "\n" + "watch it go up in flames.");
        
        FearlessTV.createItemsPlace("Mr_Perfectly_Fine", false, "What was your name agian?", true, null, null);
        
        FearlessTV.createItemsPlace("White_Horse", false, "You're not a princess, this ain't a fairy tale.", true, null, null);
        
        SpeakNow.createItemsPlace("letter", true, "The letter reads," + "\n" + "Dear John, I see it all, now it was wrong" + "\n" + "Don't you think nineteen is too young" + "\n" +
            "To be played by your dark twisted games, when I loved you so?" + "\n" + "I should've known", false, RedTV, "John Mayer takes the letter and sighs with understanding," + "\n" +
                                                                                                            "he knows exactly what this letter is about.");
        
        RedTV.createItemsPlace("bottle", true, "The bottle contains a message, but it's so faint you can hardly read it. Maybe it's heat activated ink?", false, evermore, "The flames reveal the words enscribed on the page..." + "\n" + "How is it in London? (London)" + "\n" +
            "Where are you while I'm wonderin' (wonderin')" + "\n" + "\n" + "If I'll ever see you again?" + "\n" + "The Message in a Bottle confuses you, and you are unsure which London Boy to visit next.");
        
        nineteenEightyNine.createItemsPlace("Harry_Styles", false, "Hey, I think it's been a while since you have even heard from me. What you heard is true, but I can't" + "\n" +
                                                                    "stop thinkin' about you and I-", true, null, null);
        
        reputation.createItemsPlace("snake", true, "sssssss" + "\n" + "The snake bites you, and you die.", false, null, null);
        
        reputation.createItemsPlace("dress", true, "You put on the black party dress, ready for 'New Years Day'.",false, null,null);
        
        Lover.createItemsPlace("paper_rings", true, "", false, folklore, "Joe takes the paper rings.");
        
        Lover.createItemsPlace("window", false, "You look out the widonws of this love, even though they are boarded up. It was a trap. Death by a thousand cuts.", false, null, null);
        
        folklore.createItemsPlace("cardigan", true, "You put the cardigan on, it was a little cold after all.", false, null, null);
        
        folklore.createItemsPlace("mirrorball", false, "I want you to know, I'm a mirrorball. I'll show you every version of yourself tonight." + "\n" +
        "I'll get you out on the floor, shimmering beautiful." + "\n" + "And when I break it's in a million pieces.", true, null, null);
        
        folklore.createItemsPlace("Joe", false, "There's robbers to the east, clowns to the west. I'd give you my sunshine, give you my best." + "\n" + 
                                                "But the rain is always gonna come if you're standing with me.", true, null, null);
        
        evermore.createItemsPlace("ivy", false, "", false, null, null);
    }   
    

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();       
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Taylor Swift Haunted House");
        System.out.println("Try to escape the house before time runs out :)");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
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
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("take")) {
            takeItem(command);
        }
        else if(commandWord.equals("inventory")) {
            System.out.println("\n" + getInventory());
        }
        else if(commandWord.equals("drop")) {
            dropItem(command);
        }
        else if(commandWord.equals("use")) {
            useItem(command);
        }
        else if(commandWord.equals("talk")) {
            talk(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

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
        System.out.println("   go quit help take drop inventory use talk");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        //check for second word
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        //set current room to next room
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);
        
        //if try to go west in evermore can't go outside until solve is greater than or equal to (7 if time) but probably like 3
        if(currentRoom == evermore && command.getSecondWord().equals("west") && solved < 3) {
            System.out.println();
            System.out.println("You live in a chess game, the rules change everyday. More puzzles must be solved before you can leave.");
        }
        else if(currentRoom == evermore && command.getSecondWord().equals("west") && solved >= 3) {
            currentRoom = outside;
            System.out.println();
            System.out.println(outside.getLongDescription());
        }
        
        //check less than 31
        if(move > 31) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println("It took you too long to escape! Time ran out :/");
            System.out.println(currentRoom.getLongDescription());
        }
        else if(nextRoom == null) {
            System.out.println("There is no exit!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
            move += 1;
        }
    }
    
    
    private void takeItem(Command command) {
        //take Karyn = game over
        if(inventory.size() < 13 && command.hasSecondWord() && command.getSecondWord().equals("snake")) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println("Karyn does not like being picked up.");
            System.out.println(currentRoom.getLongDescription());
        }
        //take items if they can be picked up and other specs...
        else if(inventory.size() < 13 && command.hasSecondWord() && currentRoom.getItemString().contains(command.getSecondWord()) && currentRoom.getItem(command.getSecondWord()).getWeight() == true) {
            inventory.put(command.getSecondWord(), currentRoom.getItem(command.getSecondWord()));
            System.out.println("\n" + getInventory());
            currentRoom.removeItem(command.getSecondWord());
        }
        else if(inventory.size() < 13 && command.hasSecondWord() && currentRoom.getItemString().contains(command.getSecondWord()) && currentRoom.getItem(command.getSecondWord()).getWeight() == false) {
            System.out.println("You cannot take this item.");
        }
        else if(inventory.size() < 13 && command.hasSecondWord() && !currentRoom.getItemString().contains(command.getSecondWord())) {
            System.out.println("This item is not in this room.");
        }
        else if(inventory.size() >= 13) {
            System.out.println("You cannot have more than 13 items in your inventory.");
        }
        else if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
    }
    
    private String getInventory() {
        String invString = "Inventory: ";
        Set<String> keys = inventory.keySet();
        for(String inv : keys) {
            invString += " " + inv;
        }
        return invString;
    }
    
    private void dropItem(Command command) {
        //drop glass bottle = game over
        if(command.hasSecondWord() && command.getSecondWord().equals("bottle")) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println("That was a glass bottle! The glass shatters everywhere causing major harm to yourself.");
            System.out.println(end.getLongDescription());
        }
        //take item from room and place into inventory
        else if(command.hasSecondWord() && inventory.containsKey(command.getSecondWord())) {
            currentRoom.createItemsPlace(command.getSecondWord(), inventory.get(command.getSecondWord()).getWeight(), 
            inventory.get(command.getSecondWord()).getDes(), inventory.get(command.getSecondWord()).canTalk(), inventory.get(command.getSecondWord()).getCoRoom(), 
            inventory.get(command.getSecondWord()).getCoDes());
            inventory.remove(command.getSecondWord(), inventory.get(command.getSecondWord()));
        }
        else if(command.hasSecondWord() && !inventory.containsKey(command.getSecondWord())) {
            System.out.println("You do not have that item.");
        }
        else if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
        }
    }
    
    private void useItem(Command command) {
        //use bottle
        if(command.hasSecondWord() && command.getSecondWord().equals("bottle") && currentRoom == evermore) {
            solved ++;
            System.out.println("The bottle vanishes as you watch the paper fall to the ground.");
            System.out.println(inventory.get(command.getSecondWord()).getCoDes());
            inventory.remove("bottle");
            evermore.createItemsPlace("message", true, "You pick up the paper as the flames reveal the words enscribed on the page..." + "\n" + "How is it in London? (London)" + "\n" +
            "Where are you while I'm wonderin' (wonderin')" + "\n" + "If I'll ever see you again?" + "\n" + "The Message in a Bottle confuses you, and you are unsure which London Boy to visit next.", false, folklore, "Joes happily accepts the message *without* the bottle.");
        }
        //put on dress
        else if(command.getSecondWord().equals("dress")) {
            wearingDress = true;
            System.out.println();
            System.out.println(inventory.get(command.getSecondWord()).getDes());
            inventory.remove("dress");
            solved ++;
        }
        //john mayer uses guitar = end
        else if(currentRoom == RedTV && command.getSecondWord().equals("guitar")) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println("John Mayer snatches the guitar with a grin." + "\n" + "You soon realized that you've made a mistake as he begins to play 'Why You No Love Me', and your ears bleed in response.");
            //System.out.println(Lover.getItem(command.getSecondWord()).getDes());
            System.out.println(currentRoom.getLongDescription());
        }
        //joe reads letter = end
        else if(currentRoom == folklore && command.getSecondWord().equals("letter")) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println("Joe takes your letter and is overcome with sadness. The letter wasn't meant for him but he is extremely hurt." + "\n" + 
            "His saddness quickly turns to anger. Joe takes the mirrorball from the ceiling and throws it at you.");
            //System.out.println(folklore.getItem(command.getSecondWord()).getDes());
            System.out.println(currentRoom.getLongDescription());
        }
        //death by a thousand cuts = game over
        else if(currentRoom == Lover && command.getSecondWord().equals("window")) {
            currentRoom = end;
            inventory.clear();
            System.out.println();
            System.out.println(Lover.getItem(command.getSecondWord()).getDes());
            System.out.println(currentRoom.getLongDescription());
        }
        //item used in correct room
        else if(command.hasSecondWord() && inventory.containsKey(command.getSecondWord()) && currentRoom == inventory.get(command.getSecondWord()).getCoRoom()) {
            solved ++;
            System.out.println(inventory.get(command.getSecondWord()).getCoDes());
            inventory.remove(command.getSecondWord());
        }
        //prints normal description
        else if(command.hasSecondWord() && inventory.containsKey(command.getSecondWord())) {
            System.out.println();
            System.out.println(inventory.get(command.getSecondWord()).getDes());
        }
        else if(command.hasSecondWord() && !command.getSecondWord().equals(inventory)) {
            System.out.println();
            System.out.println("You can't use this item becuase it is not in your inventory.");
        }
        else if(!command.hasSecondWord()) {
            System.out.println();
            System.out.println("Use what?");
        }
    }
    
    private void talk(Command command) {
        //check wearing dress to talk to harry
        if(!wearingDress && command.getSecondWord().equals("Harry_Styles") && currentRoom.getItemString().contains("Harry_Styles")) {
            System.out.println();
            System.out.println("Harry does not want to talk to you, you're going to need better 'style' to talk to him.");
        }
        //print if wearing dress
        else if(wearingDress && command.getSecondWord().equals("Harry_Styles") && currentRoom.getItemString().contains("Harry_Styles")) {
            System.out.println();
            System.out.println("'Ello love.");
            solved ++;
        }
        else if(command.hasSecondWord() && currentRoom.getItemString().contains(command.getSecondWord()) && currentRoom.getItem(command.getSecondWord()).canTalk()) {
            System.out.println(currentRoom.getItem(command.getSecondWord()).getDes());
        }
        else if(command.hasSecondWord() && currentRoom.getItemString().contains(command.getSecondWord()) && !currentRoom.getItem(command.getSecondWord()).canTalk() ) {
            System.out.println("You can't talk to " + command.getSecondWord() + ".");
        }
        else if(command.hasSecondWord() && !currentRoom.getItemString().contains(command.getSecondWord())) {
            System.out.println();
            System.out.println("You cannot talk to them.");
        }
        else if(!command.hasSecondWord()) {
            System.out.println("Talk to who?");
        }
    }
    
    //look
    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void printLocationInfo() {
         System.out.println(currentRoom.getLongDescription());
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
}
