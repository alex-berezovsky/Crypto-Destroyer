package com.teamthirty.buyhighselllow.Utilities;

public enum Difficulty {
    EASY {
        @Override
        public double getMultiplier() {
            return 1.0;
        }

        @Override
        public int getCash() {
            return (int) (DEFAULT_CASH * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (DEFAULT_MONUMENT_HEALTH * getMultiplier());
        }
    },
    STANDARD {
        @Override
        public double getMultiplier() {
            return 0.8;
        }

        @Override
        public int getCash() {
            return (int) (DEFAULT_CASH * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (DEFAULT_MONUMENT_HEALTH * getMultiplier());
        }
    },
    HARD {
        @Override
        public double getMultiplier() {
            return 0.6;
        }

        @Override
        public int getCash() {
            return (int) (DEFAULT_CASH * getMultiplier());
        }

        @Override
        public int getMonumentHealth() {
            return (int) (DEFAULT_MONUMENT_HEALTH * getMultiplier());
        }
    };

    private static final int DEFAULT_CASH = 1000;
    private static final int DEFAULT_MONUMENT_HEALTH = 100;

    public abstract double getMultiplier();

    public abstract int getCash();

    public abstract int getMonumentHealth();
}
