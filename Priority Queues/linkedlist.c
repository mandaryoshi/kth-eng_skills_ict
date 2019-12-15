#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node {
	int data;
	double priority;
	struct node* next;
} Node;

Node* newNode(int dataNew, double priorityNew) {
	Node* temp = (Node*)malloc(sizeof(Node));
	temp->data = dataNew;
	temp->priority = priorityNew;
	temp->next = NULL;

	return temp;
}

// value of the highest priority element
int headValue(Node** head) {
	return (*head)->data;
}

void deleteMax(Node** head) {
	Node* temp = *head;
	(*head) = (*head)->next;
	free(temp);
}

void insert(Node** head, int dataNew, double priorityNew) {
	Node* start = (*head);

	Node* temp = newNode(dataNew, priorityNew);

	// "<=" ensures FIFO
	while (start->next != NULL && start->next->priority <= priorityNew) {
		start = start->next;
	}

	// here we are at the right position
	temp->next = start->next;
	start->next = temp;
}

int isEmpty(Node** head) {
	return (*head) == NULL;
}

void insertBest(long size) {
	clock_t start_t;
	clock_t end_t;
	clock_t total_t;
	long counter;

	Node* pq = newNode(0, 555%size);
	for (counter = 0; counter <= size; counter++) {
		insert(&pq, counter, size-counter);
	}

	start_t = clock();
	for (int i = 0; i < 500; i++){
		insert(&pq, -1, -1);
	}
	end_t = clock();
	total_t = (double)(end_t - start_t);

	printf("Best case, insert\n");
	printf("Total CPU clocks to queue up %ld elements: %ld\n", size, total_t  );
	return;
}

void insertWorst(long size) {
	clock_t start_t;
	clock_t end_t;
	clock_t total_t;
	long counter;

	Node* pq = newNode(0, 555%size);
	for (counter = 0; counter <= size; counter++) {
		insert(&pq, counter, size-counter);
	}
	start_t = clock();
	for (int i = 0; i < 500; i++) {
		insert(&pq, counter, size+1+i);
	}
	end_t = clock();

/*
for (int i = 0; i < 1000; i++) {
	insert(&pq, 1, size+1);
}
*/

	total_t = (double)(end_t - start_t);

	printf("Worst case, insert\n");
	printf("Total CPU clocks to queue up %ld elements: %ld\n", size, total_t  );
	return;
}

void insertAverage(long size){

	srand(time(NULL));

	clock_t start_t;
	clock_t end_t;
	clock_t total_t;
	long counter;

	Node* pq = newNode(0, rand()%size);
	for (counter = 0; counter <= size; counter++) {
		insert(&pq, counter, size-counter);
	}
	start_t = clock();
	for (int i = 0; i < 500; i++) {
		insert(&pq, counter, rand()%size);
	}
	end_t = clock();

	total_t = (double)(end_t - start_t);

	printf("Average case, insert\n");
	printf("Total CPU clocks to queue up %ld elements: %ld\n", size, total_t  );
	return;
}

void testInsert(long size) {

	insertBest(size);
	insertAverage(size);
	insertWorst(size);

	return;
}

void testDeleteMax(long size){
	clock_t start_t;
	clock_t end_t;
	clock_t total_t;
	long counter;

	Node* pq = newNode(0, 5555%size);
	for (counter = 0; counter <= size; counter++) {
		insert(&pq, counter, size-counter);
	}

	start_t = clock();
	for (int i = 0; i < 500; i++) {
		deleteMax(&pq);
	}
	end_t = clock();

	total_t = (double)(end_t - start_t);

	printf("Any case, deleteMax\n");
	printf("Total CPU clocks to delete %ld elements: %ld\n", size, total_t  );
	return;
}

void algoTestLinkedList(int size){
	Node* pq = newNode(0, 0);
	for (int counter = 0; counter <= size; counter++) {
		insert(&pq, counter, size);
	}
	printf("List built!\n");
	for (int counter = 0; counter <= size; counter++) {
		deleteMax(&pq);
	}
	printf("List deleted!\n");
}

int main(){

	int queueSize = 10000000;

	//algoTestLinkedList(queueSize);

	testInsert(queueSize);
	printf("\n" );
	testDeleteMax(queueSize);
	printf("\n" );
	printf("-------------\n");
	testInsert(queueSize);
	printf("\n" );
	testDeleteMax(queueSize);
	printf("\n" );
/*
	testInsert(10000);
	printf("\n" );
	testInsert(100000);
	printf("\n" );
	testInsert(1000000);
	testInsert(2000000);
	testInsert(3000000);
	printf("\n" );
	printf("\n" );
	printf("\n" );
	testDeleteMax(1000);
	printf("\n" );
	testDeleteMax(10000);
	printf("\n" );
	testDeleteMax(100000);
	printf("\n" );
	testDeleteMax(1000000);
*/
	return 0;
}
