package com.mygdx.game.movedobj;

public class ActionText extends ActionBase {

    private String text;

    public ActionText(int x, int y, String text)
    {
        super(x, y, 0.5f);
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public int getCurX() {
        return (int) fromX;
    }

    @Override
    public int getCurY() {
        return (int) fromY;
    }
}
