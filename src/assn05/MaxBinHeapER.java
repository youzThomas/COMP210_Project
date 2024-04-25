package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient temp = new Patient<>(value);
        _heap.add(temp);

        int i = size() - 1;
        while(i > 0)
        {
            if(_heap.get((i - 1) / 2).compareTo(_heap.get(i)) < 0)
            {
                _heap.set(i, _heap.get((i - 1) / 2));
                _heap.set((i - 1) / 2, temp);
                i = (i - 1) / 2;
            }
            else
            {
                break;
            }
        }
    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient temp = new Patient<>(value, priority);
        _heap.add(temp);

        int i = size() - 1;
        while(i > 0)
        {
            if(_heap.get((i - 1) / 2).compareTo(_heap.get(i)) < 0)
            {
                _heap.set(i, _heap.get((i - 1) / 2));
                _heap.set((i - 1) / 2, temp);
                i = (i - 1) / 2;
            }
            else
            {
                break;
            }
        }
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()){
            return null;
        }
        Prioritized<V, P> maxPatient = _heap.get(0);
        Prioritized<V, P> lastPatient = _heap.remove(_heap.size() - 1);

        if (_heap.isEmpty()){
            return lastPatient.getValue();
        }

        _heap.set(0, lastPatient);
        int index = 0;
        while (true){
            int maxIndex = index;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex < _heap.size() && _heap.get(leftChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                maxIndex = leftChildIndex;
            }
            if (rightChildIndex < _heap.size() && _heap.get(rightChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                maxIndex = rightChildIndex;
            }

            if (index == maxIndex) {
                break;
            }

            Prioritized<V, P> temp = _heap.get(index);
            _heap.set(index, _heap.get(maxIndex));
            _heap.set(maxIndex, temp);
            index = maxIndex;
        }

        return maxPatient.getValue();
    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
        if (_heap.isEmpty()) {
            return null;
        }
        return _heap.get(0).getValue();
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        int patientIndex = -1;
        for (int i = 0; i < _heap.size(); i++) {
            if (_heap.get(i).getValue().equals(value)) {
                patientIndex = i;
                break;
            }
        }
        if (patientIndex == -1) {
            return;
        }


        Prioritized<V, P> oldPatient = _heap.get(patientIndex);
        P oldPriority = oldPatient.getPriority();


        Prioritized<V, P> newPatient = new Patient<>(oldPatient.getValue(), newPriority);
        _heap.set(patientIndex, newPatient);


        if (oldPriority != null && oldPriority.compareTo(newPriority) < 0) {
            bubbleUp(patientIndex);
        } else if (oldPriority != null && oldPriority.compareTo(newPriority) > 0) {
            bubbleDown(patientIndex);
        }
    }


    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && _heap.get(index).getPriority().compareTo(_heap.get(parentIndex).getPriority()) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int maxIndex = index;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex < _heap.size() && _heap.get(leftChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                maxIndex = leftChildIndex;
            }
            if (rightChildIndex < _heap.size() && _heap.get(rightChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                maxIndex = rightChildIndex;
            }

            if (index == maxIndex) {
                break;
            }

            swap(index, maxIndex);
            index = maxIndex;
        }
    }

    private void swap(int i, int j) {
        Prioritized<V, P> temp = _heap.get(i);
        _heap.set(i, _heap.get(j));
        _heap.set(j, temp);
    }


    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (Prioritized<V, P> entry : initialEntries) {
            _heap.add(entry);
        }
        for (int i = _heap.size() / 2 - 1; i >= 0; i--) {
            int index = i;
            while (true) {
                int maxIndex = index;
                int leftChildIndex = 2 * index + 1;
                int rightChildIndex = 2 * index + 2;

                if (leftChildIndex < _heap.size() && _heap.get(leftChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                    maxIndex = leftChildIndex;
                }
                if (rightChildIndex < _heap.size() && _heap.get(rightChildIndex).getPriority().compareTo(_heap.get(maxIndex).getPriority()) > 0) {
                    maxIndex = rightChildIndex;
                }

                if (index == maxIndex) {
                    break;
                }

                Prioritized<V, P> temp = _heap.get(index);
                _heap.set(index, _heap.get(maxIndex));
                _heap.set(maxIndex, temp);
                index = maxIndex;
            }
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

}
