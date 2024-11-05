package nc.server.nc.server.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public interface PollService {
    void updateData();
    String waitForUpdate() throws ExecutionException, InterruptedException;
}
