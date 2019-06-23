package sample;

 class Knights extends  Queen {

     Knights(int frstPositionX, int frstPositionY) {
        super(frstPositionX, frstPositionY);
    }

    @Override
    boolean xod(int lastPositionX, int lastPositionY) {
        return (lastPositionX-frstPositionX==2 || frstPositionX-lastPositionX==2) &&(lastPositionY==frstPositionY+1 || lastPositionY==frstPositionY-1) ||
                (lastPositionX-frstPositionX==1 || frstPositionX-lastPositionX==1) &&(lastPositionY==frstPositionY+2 || lastPositionY==frstPositionY-2);
    }
}
