package edu.austral.dissis.valueobject;

public enum SizeUnit {
    BYTES {
        @Override
        public long toBytes(long size) {
            return size;
        }
        
        @Override
        public long fromBytes(long bytes) {
            return bytes;
        }
    },
    
    KILOBYTES {
        @Override
        public long toBytes(long size) {
            return size * 1024L;
        }
        
        @Override
        public long fromBytes(long bytes) {
            return bytes / 1024L;
        }
    },
    
    MEGABYTES {
        @Override
        public long toBytes(long size) {
            return size * 1024L * 1024L;
        }
        
        @Override
        public long fromBytes(long bytes) {
            return bytes / (1024L * 1024L);
        }
    },
    
    GIGABYTES {
        @Override
        public long toBytes(long size) {
            return size * 1024L * 1024L * 1024L;
        }
        
        @Override
        public long fromBytes(long bytes) {
            return bytes / (1024L * 1024L * 1024L);
        }
    },
    
    TERABYTES {
        @Override
        public long toBytes(long size) {
            return size * 1024L * 1024L * 1024L * 1024L;
        }
        
        @Override
        public long fromBytes(long bytes) {
            return bytes / (1024L * 1024L * 1024L * 1024L);
        }
    };
    
    public abstract long toBytes(long size);
    public abstract long fromBytes(long bytes);
}
