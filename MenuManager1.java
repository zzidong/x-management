//경상국립대 객체지향언어 정지원



package chap1.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import log.EventLogger;

public class MenuManager {
   private static final Object ScheduleManager = null;
   static EventLogger logger = new EventLogger();
   
   public static void main(String[] args) {
      
      Scanner input = new Scanner(System.in);
      ResearchScheduleManager ResearchScheduleManager = getObject("ResearchScheduleManager.ser");
      if (ResearchScheduleManager == null) {
         ResearchScheduleManager = newScheduleManager();
      }
            
      selectMenu(input, ResearchScheduleManager);
      putObject(ResearchScheduleManager, "ResearchScheduleManager.ser");
   }
   
   private static ResearchScheduleManager newScheduleManager() {
      // TODO Auto-generated method stub
      return null;
   }

   public static void selectMenu(Scanner input,ResearchScheduleManager ScheduleManager) {
      int num = -1;
      while (num != 5) {
         try {
            showMenu();
            num = input.nextInt();
            switch(num) {
            case 1:
               ResearchScheduleManager.addSchedule();
               logger.log("add a Schedule");
               break;
            case 2:
               ResearchScheduleManager.deleteSchedule();
               logger.log("delete a Schedule");
               break;
            case 3:
               ResearchScheduleManager.editSchedule();
               logger.log("edit a Schedule");
               break;
            case 4:
               ResearchScheduleManager.viewSchedule();
               logger.log("view Schedule");
               break;
            default:
               continue;            
            }
         }
         catch(InputMismatchException e) {
            System.out.println("Please put an integer between 1 and 5!");
            if (input.hasNext()) {
               input.next();
            }
            num = -1;
         }
      }      
   }
   
   public static void showMenu() {
      System.out.println("ResearchSchedule System Menu ***");
      System.out.println(" 1. Add Schedule");
      System.out.println(" 2. Delete Schedule");
      System.out.println(" 3. Edit Schedules");
      System.out.println(" 4. View Schedule");
      System.out.println(" 5. Exit");
      System.out.println("Select one number between 1 - 6:");
   }
   
   public static ResearchScheduleManager getObject(String filename) {
      ResearchScheduleManager ScheduleManager = null;
      
      
      
      try {
         FileInputStream file = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(file);
         
         ScheduleManager = (ResearchScheduleManager)in.readObject();
         
         in.close();
         file.close();   
      } catch (FileNotFoundException e) {
         return ScheduleManager;
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return ScheduleManager;
   }
   
   public static void putObject(ResearchScheduleManager researchScheduleManager, String filename) {
      try {
         FileOutputStream file = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(file);
         
         out.writeObject(ScheduleManager);
         
         out.close();
         file.close();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
