/*
 * BeanNumberToLetter.java
 *
 * Created on 1 de abril de 2007, 12:07 AM
 * Recibe un importe con nï¿½mero y lo transforma en importe con letra.
 */

package com.vos.components.javabeans;

import java.io.Serializable;

/**
 * @author HP_Propietario
 */
public class BeanNumberToLetter extends Object implements Serializable {
    
	private static final long serialVersionUID = -1453074879813438699L;
	/* Interfaz pï¿½blica del bean */
    /* Campos privados del bean. */
    private String numString;
    private String currencyToLetter;
    private char[] numToChars;
    private int numberOfChars;
    private int idx;

    /*
     * Constructor por default del JavaBeans
     */
    
    public BeanNumberToLetter() {
    }    
    
    /*
     * Asigna el importe a convertir.
     */
    public void setNumber(String numString) {  

        if (numString.equals("")) 
            this.numString = "0";
                     
        try {
            Integer.parseInt(numString);
            this.numString = numString;
        } catch (Exception e) {
            this.currencyToLetter = "Error de conversión: " + e.getMessage();
        }
    }
            
    /*
     * Obtiene el importe introducido
     */
    public String getNumber() {
        return  this.numString;
    }
        
    
    /*
     * Obtiene el importe en letra.
     */
    public String getNumberToLetter() {  
    	// variable para invertir la cantidad introducida.
    	StringBuilder inversa = new StringBuilder(numString);
    	
        numberOfChars = numString.length();
        numToChars = new char[numberOfChars];                       
        
        numToChars = inversa.reverse().toString().toCharArray();
        currencyToLetter = "";

        idx = numberOfChars-1;
        while (idx >= 0)
        {
        	ConvierteLetra(idx);
        	idx--;
        }
                        
        if (numberOfChars <= 9)
        	currencyToLetter  += "  " + "M.N.";
        
        return  currencyToLetter;                
    }
    

    /* Mï¿½todos privados del bean */
    private void ConvierteLetra(int Item)
    {
        int Uni = 0, Dec = 0, Cen = 0;

        switch (Item)
        {
            case 8: // Centenas de Millón
                Cen = Integer.parseInt(String.valueOf(numToChars[8]) + 
                                       String.valueOf(numToChars[7]) + 
                                       String.valueOf(numToChars[6]) );

                if (Cen > 100)
                    this.currencyToLetter = this.currencyToLetter + Centenas(Cen);
                break;
            case 7: // Decenas de Millón
                Dec = Integer.parseInt( String.valueOf(numToChars[7]) + String.valueOf(numToChars[6]) );
                
                if (Dec > 10) 
                    this.currencyToLetter = this.currencyToLetter + Decenas(Dec);
                break;
            case 6: // Unidades de Millón
                Uni = Integer.parseInt( String.valueOf(numToChars[6]) );
                
                if (Uni != 0) {
                    Dec = 0;
                    if (numberOfChars > 7) 
                       Dec = Integer.parseInt( String.valueOf(numToChars[7]) );                                            

                    if ( (Dec == 0) && (Uni == 1) )
                        this.currencyToLetter = this.currencyToLetter + Unidades(Uni) + " MILLÓN ";
                    else if ((Dec == 0) && (Uni > 1))
                        this.currencyToLetter = this.currencyToLetter + Unidades(Uni) + " MILLONES ";
                    else if ((Dec != 1) && (Uni > 0))
                        this.currencyToLetter = this.currencyToLetter + Unidades(Uni) + " MILLONES ";
                    else if (Dec == 1)
                        this.currencyToLetter = this.currencyToLetter + " MILLONES ";
                }
                break;
            case 5: // Centenas de Millar
                Cen = Integer.parseInt( String.valueOf(numToChars[5]) + 
                                        String.valueOf(numToChars[4]) +
                                        String.valueOf(numToChars[3]));                

                if (Cen >= 100) {

                    if ((Cen % 100) == 0)
                        this.currencyToLetter = this.currencyToLetter + Centenas(Cen) + " MIL ";
                    else
                        this.currencyToLetter = this.currencyToLetter + Centenas(Cen);
                }
                break;
            case 4: // Decenas de Millar
                Dec = Integer.parseInt( String.valueOf(numToChars[4]) + String.valueOf(numToChars[3]) );

                if (Dec >= 10) {
                    if ( ((Dec >= 10) && (Dec < 20)) ||  ((Dec % 10) == 0) )
                        this.currencyToLetter = this.currencyToLetter + Decenas(Dec) + " MIL ";
                    else
                        this.currencyToLetter = this.currencyToLetter + Decenas(Dec);                
                }
                break;
            case 3: // Unidades de Millar
                Uni = Integer.parseInt( String.valueOf(numToChars[3]) );

                if (Uni != 0) {

                    if (numberOfChars > 4)
                        Dec = Integer.parseInt( String.valueOf(numToChars[4]) );                

                    if ((Dec != 1) && (Uni > 0))
                        this.currencyToLetter = this.currencyToLetter + Unidades(Uni) + " MIL ";
                }
                break;
            case 2: // Centenas 100..999
                Cen = Integer.parseInt( String.valueOf(numToChars[2]) + 
                                        String.valueOf(numToChars[1]) + 
                                        String.valueOf(numToChars[0]) );
                if (Cen >= 100)
                    this.currencyToLetter = this.currencyToLetter + Centenas(Cen);
                break;
            case 1: // Decenas 10..99
                Dec = Integer.parseInt( String.valueOf(numToChars[1]) + String.valueOf(numToChars[0]) );

                if (Dec >= 10) 
                    this.currencyToLetter = this.currencyToLetter + Decenas(Dec);
                break;
            case 0: // Unidades 0..9
                Uni = Integer.parseInt( String.valueOf(numToChars[0]) );
                Dec = 0;

                if (numberOfChars == 1)
                    this.currencyToLetter = Unidades(Uni);
                else
                {
                    if (numToChars[1] != '0')
                        Dec = Integer.parseInt( String.valueOf(numToChars[1]) );


                    if ((Dec != 1) && (Uni > 0))
                        this.currencyToLetter = this.currencyToLetter + Unidades(Uni);
                }
                break;
            default:
                this.currencyToLetter = "La cantidad es muy grande.";
                idx = -1;
                break;
        }
    }    

    private String Unidades(int unidades)
    {
        String units = "";

        switch (unidades)
        {
            case 0:
                units = "CERO";
                break;
            case 1:
                units = "UNO";
                break;
            case 2:
                units = "DOS";
                break;
            case 3:
                units = "TRES";
                break;
            case 4:
                units = "CUATRO";
                break;
            case 5:
                units = "CINCO";
                break;
            case 6:
                units = "SEIS";
                break;
            case 7:
                units = "SIETE";
                break;
            case 8:
                units = "OCHO";
                break;
            case 9:
                units = "NUEVE";
                break;
        }

        return units;
    }

    private String Decenas(int decens)
    {
        StringBuffer dec = new StringBuffer(decens + "");
        int decenas = Integer.parseInt(dec.toString());
        String tens = "";

        if (decenas >= 10 && decenas < 20)
        {
            switch (decenas)
            {
                case 10: tens = "DIEZ"; break;
                case 11: tens = "ONCE"; break;
                case 12: tens = "DOCE"; break;
                case 13: tens = "TRECE"; break;
                case 14: tens = "CATORCE"; break;
                case 15: tens = "QUINCE"; break;
                case 16: tens = "DIECISï¿½IS"; break;
                case 17: tens = "DIECISIETE"; break;
                case 18: tens = "DIECIOCHO"; break;
                case 19: tens = "DIECINUEVE"; break;
            }
        }        
        else if (decenas == 20)
        {
            tens = "VEINTE";
        }
        else if (decenas >= 21 && decenas <= 29)
        {
            tens = "VEINTI";
        }
        else if (decenas == 30)
        {
            tens = "TREINTA";
        }
        else if (decenas >= 31 && decenas <= 39)
        {
            tens = "TREINTA Y ";
        }
        else if (decenas == 40)
        {
            tens = "CUARENTA";
        }
        else if (decenas >= 41 && decenas <= 49)
        {
            tens = "CUARENTA Y ";
        }
        else if (decenas == 50)
        {
            tens = "CINCUENTA";
        }
        else if (decenas >= 51 && decenas <= 59)
        {
            tens = "CINCUENTA Y ";
        }
        else if (decenas == 60)
        {
            tens = "SESENTA";
        }
        else if (decenas >= 61 && decenas <= 69)
        {
            tens = "SESENTA Y ";
        }
        else if (decenas == 70)
        {
            tens = "SETENTA";
        }
        else if (decenas >= 71 && decenas <= 79)
        {
            tens = "SETENTA Y ";
        }
        else if (decenas == 80)
        {
            tens = "OCHENTA";
        }
        else if (decenas >= 81 && decenas <= 89)
        {
            tens = "OCHENTA Y ";
        }
        else if (decenas == 90)
        {
            tens = "NOVENTA";
        }
        else if (decenas >= 91 && decenas <= 99)
        {
            tens = "NOVENTA Y ";
        }

        return tens;
    }

    private String Centenas(int centena)
    {
        String cent = centena + "";
        int centenas = Integer.parseInt(cent);
        String hundred = "";

        if (centenas == 100)
        {
            hundred = "CIEN";
        }
        else if (centenas >= 101 && centenas <= 199)
        {
            hundred = "CIENTO ";
        }
        else if (centenas >= 200 && centenas <= 299)
        {
            hundred = "DOSCIENTOS ";
        }
        else if (centenas >= 300 && centenas <= 399)
        {
            hundred = "TRESCIENTOS ";
        }
        else if (centenas >= 400 && centenas <= 499)
        {
            hundred = "CUATROCIENTOS ";
        }
        else if (centenas >= 500 && centenas <= 599)
        {
            hundred = "QUINIENTOS ";
        }
        else if (centenas >= 600 && centenas <= 699)
        {
            hundred = "SEISCIENTOS ";
        }
        else if (centenas >= 700 && centenas <= 799)
        {
            hundred = "SETECIENTOS ";
        }
        else if (centenas >= 800 && centenas <= 899)
        {
            hundred = "OCHOCIENTOS ";
        }
        else if (centenas >= 900 && centenas <= 999)
        {
            hundred = "NOVECIENTOS ";
        }

        return hundred;
    }   
    
    
     
    

}
