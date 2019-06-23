package sample;

 class Bishops extends  Queen {

     Bishops(int frstPositionX, int frstPositionY) {
        super(frstPositionX, frstPositionY);
    }

    @Override
    boolean xod(int lastPositionX, int lastPositionY) {
        return (frstPositionX - frstPositionY) == (lastPositionX - lastPositionY) || (frstPositionX + frstPositionY) == (lastPositionX + lastPositionY);

    }
}
