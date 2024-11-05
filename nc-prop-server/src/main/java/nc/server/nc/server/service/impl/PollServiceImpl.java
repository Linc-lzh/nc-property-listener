package nc.server.nc.server.service.impl;

import com.sun.xml.internal.ws.util.CompletedFuture;
import nc.server.nc.server.service.PollService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class PollServiceImpl implements PollService {
    private String data = "Init data";

    public void updateData(){
        try{
            TimeUnit.SECONDS.sleep(10);
            data = "Updated data";
            System.out.println("Update is done.");
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String waitForUpdate() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            try{
                while(!data.equals("Updated data")){
                    System.out.println("wait for changes...");
                    TimeUnit.SECONDS.sleep(1);
                }
                future.complete(data);
            }
            catch (InterruptedException e){
                future.completeExceptionally(e);
            }
        }).start();

        return future.get();
    }
}
