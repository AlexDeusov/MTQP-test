# MTQP-test
Multithreading test task.

Once per minute are going the JSON reading, using Jackson JSON converts to Bid objects. 
By the bid type objects distribute to queues if they have not been there yet. 
If the queue's head is present it polls and going to a new thread for logging.
