package de.hhn.it.pp.components.librarymanagementsystem.provider;

public class Fine {
    private int fine;
    private int finePerDay = 5;

    public Fine(int elapsedDays) {
        this.fine = elapsedDays * finePerDay;
    }

    /**
     * Sets fine per day.
     *
     * @param finePerDay fine per day
     */
    public void setFinePerDay(int finePerDay) {
        this.finePerDay = finePerDay;
    }

    /**
     * Returns the fine per day.
     *
     * @return fine per day
     */
    public int getFinePerDay() {
        return this.finePerDay;
    }

    /**
     * Returns the elapsed day (days after deadline).
     *
     * @return elapsed day (days after deadline)
     */
    public int getFine() {
        return fine;
    }

    /**
     * Sets the fine.
     *
     * @return total fine
     */
    public void setFine(int fine) {
        this.fine = fine;
    }
}

