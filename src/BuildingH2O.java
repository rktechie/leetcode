import java.util.concurrent.Semaphore;

/*
1117. Building H2O
Medium
Topics
Companies
There are two kinds of threads: oxygen and hydrogen. Your goal is to group these threads to form water molecules.

There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it must wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it must wait for an oxygen thread and another hydrogen thread.
We do not have to worry about matching the threads up explicitly; the threads do not necessarily know which other threads they are paired up with. The key is that threads pass the barriers in complete sets; thus, if we examine the sequence of threads that bind and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.



Example 1:

Input: water = "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
Example 2:

Input: water = "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
 */
public class BuildingH2O {
  // Semaphores to control the release of hydrogen and oxygen atoms.
  private Semaphore semaphoreHydrogen = new Semaphore(2); // hydrogen semaphore initialized to 2 permits, since we need 2 H for every O
  private Semaphore semaphoreOxygen = new Semaphore(0); // oxygen semaphore initialized with 0 permits, will be released by hydrogen

  public H2O() {
    // Constructor for H2O, nothing needed here since semaphores are initialized above
  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    // Acquire a permit for releasing a hydrogen atom
    semaphoreHydrogen.acquire();
    // releaseHydrogen.run() outputs "H"
    releaseHydrogen.run();
    // Release a permit for oxygen, signaling that one H has been released
    semaphoreOxygen.release();
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    // Acquire two permits for releasing an oxygen atom as we need two hydrogen atoms before releasing one oxygen atom
    semaphoreOxygen.acquire(2);
    // releaseOxygen.run() outputs "O"
    releaseOxygen.run();
    // Release two permits for hydrogen, allowing the release of two hydrogen atoms
    semaphoreHydrogen.release(2);
  }
}
