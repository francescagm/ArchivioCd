package it.unibs.ing.fp.archivioCD2;

import javax.swing.JOptionPane;
public class Secondi1
{
   public static void main(String args[])
   {
       long sec,  // input
            ore,  // output
            min,
            sec2;
       String stringa = JOptionPane.showInputDialog("Quanti secondi?");
       sec = Long.parseLong(stringa);
       ore  = sec/3600;
       sec2 = sec-3600*ore;
       min  = sec2/60;
       sec2 = sec2-60*min;
       JOptionPane.showMessageDialog(null,
           "sec = " + sec + "\n" +
           "ore = " + ore + "\n" +
           "min = " + min + "\n" +
           "sec = " + sec2       );
   }//main()
}//Secondi1