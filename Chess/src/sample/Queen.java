package sample;

 class Queen extends Chess {
    int frstPositionX;
    int frstPositionY;

     Queen(int frstPositionX, int frstPositionY) {
        this.frstPositionX = frstPositionX;
        this.frstPositionY = frstPositionY;
    }


    @Override
    boolean xod(int lastPositionX, int lastPositionY) {

        return frstPositionY == lastPositionY && frstPositionX != lastPositionX || frstPositionY != lastPositionY && frstPositionX == lastPositionX ||
                (frstPositionX - frstPositionY) == (lastPositionX - lastPositionY) || (frstPositionX + frstPositionY) == (lastPositionX + lastPositionY);
    }
}
