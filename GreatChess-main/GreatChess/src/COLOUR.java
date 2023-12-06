public enum COLOUR {
    B {
        @Override
        public String toString() {
            return "Чёрные";
        }

        @Override
        public String toSmallString() {
            return "b";
        }
    },

    W {
        @Override
        public String toString() {
            return "Белые";
        }

        @Override
        public String toSmallString() {
            return "w";
        }
    };

    public abstract String toSmallString();

    public static COLOUR not(COLOUR colour) {
        if (colour == COLOUR.B)
            return COLOUR.W;
        else
            return COLOUR.B;

    }

}
