package sample;

 class Rooks extends Queen {
     Rooks(int frstPositionX, int frstPositionY) {
        super(frstPositionX, frstPositionY);
    }

    @Override
    boolean xod(int lastPositionX, int lastPositionY) {
        return frstPositionY == lastPositionY && frstPositionX != lastPositionX || frstPositionY != lastPositionY && frstPositionX == lastPositionX ;
    }
}
