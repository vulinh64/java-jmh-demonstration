package com.vulinh;

import java.io.IOException;
import java.security.SecureRandom;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;

@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@Fork(value = 1, warmups = 2)
@Threads(-1)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@State(Scope.Thread)
public class BenchmarkRunner {

  private static final int SIZE = 10000;

  private static final long[] ARRAY = new long[SIZE];

  static {
    var random = new SecureRandom();

    for (int i = 0; i < SIZE; i++) {
      ARRAY[i] = random.nextLong();
    }
  }

  public static void main(String[] args) throws IOException {
    Main.main(args);
  }

  @Benchmark
  public int testDivisibleByZeroBitwise() {
    var count = 0;

    for (var e : ARRAY) {
      if ((e & 1) == 0) {
        count++;
      }
    }

    return count;
  }

  @Benchmark
  public int testDivisibleByZeroModulo() {
    var count = 0;

    for (var e : ARRAY) {
      if (e % 2 == 0) {
        count++;
      }
    }

    return count;
  }
}
