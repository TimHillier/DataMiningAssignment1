package com.company;
//This is the object that holds the element and the number of them.

public class Block {
    private String Element;
    private int Amount;


    public Block()
    {
        Element = "-A";
        Amount = -1;
    }

    public Block(String _element, int _amount)
    {
        this.Element = _element;
        this.Amount = _amount;
    }

    public int getAmount()
    {
        return Amount;

    }
    public String getElement()
    {
        return Element;
    }
    public void setAmount(int a)
    {
        Amount = a;
    }
    //increases by one.
    public void incrementAmount()
    {
        Amount++;
    }
}