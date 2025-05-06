package edu.austral.dissis.valueobject;

import java.util.Objects;

public final class DataSize {
    private final long bytes;
    
    public static final DataSize ZERO = new DataSize(0);
    
    private DataSize(long bytes) {
        this.bytes = bytes;
    }
    
    public static DataSize ofBytes(long bytes) {
        return new DataSize(bytes);
    }
    
    public static DataSize ofKilobytes(long kilobytes) {
        return new DataSize(kilobytes * 1024L);
    }
    
    public static DataSize ofMegabytes(long megabytes) {
        return new DataSize(megabytes * 1024L * 1024L);
    }
    
    public static DataSize ofGigabytes(long gigabytes) {
        return new DataSize(gigabytes * 1024L * 1024L * 1024L);
    }
    
    public static DataSize ofTerabytes(long terabytes) {
        return new DataSize(terabytes * 1024L * 1024L * 1024L * 1024L);
    }
    
    public static DataSize of(long value, SizeUnit unit) {
        return new DataSize(unit.toBytes(value));
    }
    
    public long toBytes() {
        return bytes;
    }
    
    public long toKilobytes() {
        return bytes / 1024L;
    }
    
    public long toMegabytes() {
        return bytes / (1024L * 1024L);
    }
    
    public long toGigabytes() {
        return bytes / (1024L * 1024L * 1024L);
    }
    
    public long toTerabytes() {
        return bytes / (1024L * 1024L * 1024L * 1024L);
    }
    
    public long to(SizeUnit unit) {
        return unit.fromBytes(bytes);
    }
    
    public DataSize add(DataSize other) {
        return new DataSize(this.bytes + other.bytes);
    }
    
    public DataSize subtract(DataSize other) {
        return new DataSize(this.bytes - other.bytes);
    }
    
    public DataSize multiply(long multiplier) {
        return new DataSize(this.bytes * multiplier);
    }
    
    public boolean isLargerThan(DataSize other) {
        return this.bytes > other.bytes;
    }
    
    public boolean isLargerThanOrEqualTo(DataSize other) {
        return this.bytes >= other.bytes;
    }
    
    public boolean isSmallerThan(DataSize other) {
        return this.bytes < other.bytes;
    }
    
    public boolean isSmallerThanOrEqualTo(DataSize other) {
        return this.bytes <= other.bytes;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSize dataSize = (DataSize) o;
        return bytes == dataSize.bytes;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bytes);
    }
    
    @Override
    public String toString() {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return toKilobytes() + " KB";
        } else if (bytes < 1024 * 1024 * 1024) {
            return toMegabytes() + " MB";
        } else if (bytes < 1024L * 1024 * 1024 * 1024) {
            return toGigabytes() + " GB";
        } else {
            return toTerabytes() + " TB";
        }
    }
}
