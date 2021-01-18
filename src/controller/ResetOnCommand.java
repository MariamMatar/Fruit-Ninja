package controller;



public class ResetOnCommand implements Command{
	
		
		private Reciever reciever;
		public ResetOnCommand(Reciever reciever) {
			this.reciever=reciever;
		}

		public void execute() {
			getReciever().reset();
		}

    /**
     * @return the reciever
     */
    public Reciever getReciever() {
        return reciever;
    }

    /**
     * @param reciever the reciever to set
     */
    public void setReciever(Reciever reciever) {
        this.reciever = reciever;
    }
	

}