package sample;

 class King extends Queen {
     King(int frstPositionX, int frstPositionY) {
        super(frstPositionX, frstPositionY);
    }

    @Override
    boolean xod(int lastPositionX, int lastPositionY) {
        return (frstPositionY == lastPositionY && (lastPositionX == frstPositionX + 1)||(lastPositionX==frstPositionX-1)) ||
                (frstPositionX == lastPositionX && (lastPositionY == frstPositionY + 1)||(lastPositionY==frstPositionY-1))||
                ((lastPositionX-frstPositionX==1||frstPositionX-lastPositionX==1)&&(lastPositionY-frstPositionY==1)||(frstPositionY-lastPositionY==1))||
                ((lastPositionX+frstPositionX==1||frstPositionX+lastPositionX==1)&&(lastPositionY-frstPositionY==1||(frstPositionY-lastPositionY==1)));
    }
}
