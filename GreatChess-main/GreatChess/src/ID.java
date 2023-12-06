public enum ID {
    KING {
        @Override
        public String toString() {
            return "K";
        }
        public String toFullString() {
            return "King";
        }
    },
    QUEEN {
        @Override
        public String toString() {
            return "Q";
        }
        public String toFullString() {
            return "Queen";
        }
    },
    ROOK {
        @Override
        public String toString() {
            return "R";
        }
        public String toFullString() {
            return "Rook";
        }
    },
    BISHOP {
        @Override
        public String toString() {
            return "B";
        }
        public String toFullString() {
            return "Bishop";
        }
    },
    KNIGHT {
        @Override
        public String toString() {
            return "N";
        }
        public String toFullString() {
            return "Knight";
        }
    },
    PAWN {
        @Override
        public String toString() {
            return "";
        }
        public String toFullString() {
            return "Pawn";
        }
    },

    VIZAR {
        @Override
        public String toString() {
            return "";
        }
        public String toFullString() {
            return "Vizar";
        }
    },

    WARCAR {
        @Override
        public String toString() {
            return "";
        }
        public String toFullString() {
            return "WarCar";
        }
    },


    GIRAFFE {
        @Override
        public String toString() { return ""; }
        public String toFullString() { return "Giraffe"; }
    };

    public abstract String toFullString();

}
