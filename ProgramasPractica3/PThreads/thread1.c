#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#define NUM_THREADS 5

void *PrintHello(void *threadid)
{
   long tid;
   tid = (long)threadid;
   // Para dormir a los hilos y poder mediante terminal visualizarlos
   sleep(15);
   printf("Hello World! It's me, thread #%ld!\n", tid);
   pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
   pthread_t threads[NUM_THREADS];
   int rc;
   long t;
   // Para obtener el PID del proceso main
   // este coincide con el arrogado por el comando ps
   printf("PID:%d\n", getpid());
   for (t = 0; t < NUM_THREADS; t++)
   {
      printf("In main: creating thread %ld\n", t);
      rc = pthread_create(&threads[t], NULL, PrintHello, (void *)t);
      if (rc)
      {
         printf("ERROR; return code from pthread_create() is %d\n", rc);
         exit(-1);
      }
   }
   // Para que el hilo main no termine sino terminana sus hijos
   for (t = 0; t < NUM_THREADS; t++)
   {
      pthread_join(threads[t], NULL);
   }
   printf("Exit ...");

   /* Last thing that main() should do */
   pthread_exit(NULL);
}
