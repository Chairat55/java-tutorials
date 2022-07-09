package liquibase.ext;

import liquibase.exception.DatabaseException;
import liquibase.exception.LockException;
import liquibase.lockservice.StandardLockService;
import liquibase.logging.LogService;
import liquibase.logging.LogType;

import java.util.Date;

public class ForceReleaseLockService extends StandardLockService {

    @Override
    public int getPriority() {
        return super.getPriority()+1;
    }

    @Override
    public void waitForLock() throws LockException {
        int waitingForLockCount =0;
        boolean locked = false;
        long timeToGiveUp = (new Date()).getTime() + this.getChangeLogLockWaitTime() * 1000L * 60L;
        while(!locked && (new Date()).getTime() < timeToGiveUp){
            if(waitingForLockCount==2) break;
            locked = super.acquireLock();
            if (!locked) {
                LogService.getLog(this.getClass()).info(LogType.LOG, "Waiting for changelog lock....");
                try {
                    Thread.sleep(this.getChangeLogLockRecheckTime() * 1000L);
                } catch (InterruptedException var7) {
                    Thread.currentThread().interrupt();
                }
            }
            waitingForLockCount++;
        }
        try {
            super.forceReleaseLock();
        } catch (DatabaseException e) {
            throw new LockException("Could not enforce getting the lock.", e);
        }
        super.waitForLock();
    }


}
