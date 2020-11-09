# MTQP-test
Multithreading test task.

Once per minute are going the JSON reading, using Jackson JSON convert to Bid objects. 
By bid type objects distribute to queues if they have not been there yet. 
If queue's head present it polls and going to new thread for logging.
