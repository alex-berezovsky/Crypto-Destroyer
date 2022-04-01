package com.teamthirty.buyhighselllow.Utilities;

public enum Difficulty {
    EASY {
        @Override
        public double getMultiplier() {
            return 1.0;
        }

        @Override
        public int getCash() {
            return (int) (defaultCash * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (defaultMonumentHealth * getMultiplier());
        }
    },
    STANDARD {
        @Override
        public double getMultiplier() {
            return 0.8;
        }

        @Override
        public int getCash() {
            return (int) (defaultCash * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (defaultMonumentHealth * getMultiplier());
        }
    },
    HARD {
        @Override
        public double getMultiplier() {
            return 0.6;
        }

        @Override
        public int getCash() {
            return (int) (defaultCash * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (defaultMonumentHealth * getMultiplier());
        }
    };

    public abstract double getMultiplier();

    public abstract int getCash();
    public abstract int getMonumentHealth();

    private static final int defaultCash = 1000;
    private static final int defaultMonumentHealth = 100;
}
