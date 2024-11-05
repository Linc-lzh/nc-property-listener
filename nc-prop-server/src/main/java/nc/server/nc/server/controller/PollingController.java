package nc.server.nc.server.controller;

import nc.server.nc.server.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class PollingController {

    @Autowired
    private PollService pollService;

    @GetMapping("/poll")
    public String poll() throws ExecutionException, InterruptedException {
        return pollService.waitForUpdate();
    }

    @GetMapping("/update")
    public void update() {
        pollService.updateData();
    }
}
