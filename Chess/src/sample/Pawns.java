package sample;

 class Pawns extends Queen {


     Pawns(int frstPositionX, int frstPositionY) {
        super(frstPositionX, frstPositionY);
    }

    @Override
    boolean xod(int lastPositionX, int lastPositionY) {
        if (frstPositionY==2){
            return  frstPositionX==lastPositionX && (frstPositionY+1==lastPositionY||frstPositionY+2==lastPositionY);
        }else {
            if (frstPositionY!=1&& frstPositionY!=8){
                return frstPositionX==lastPositionX&&frstPositionY+1==lastPositionY;
            }
        }
        return false;
    }
}
