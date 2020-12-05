package com.example.application_dontfailme.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application_dontfailme.R;
import com.example.application_dontfailme.data.model.JournalEntry;
import com.example.application_dontfailme.databinding.CardJournalDetailBinding;
import com.example.application_dontfailme.viewmodel.JournalVM;

import java.util.List;


public class JournalListAdapter extends RecyclerView.Adapter<JournalListAdapter.ViewHolder> {


    private JournalVM journalVM;
    private LifecycleOwner lifecycleOwner;

    public JournalListAdapter(JournalVM vm, LifecycleOwner lifecycleOwner) {
        this.journalVM = vm;
        this.lifecycleOwner = lifecycleOwner;

        vm.getListEntries().observe(lifecycleOwner, e -> notifyDataSetChanged());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardJournalDetailBinding binding = CardJournalDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setLifecycleOwner(lifecycleOwner);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setEntry(journalVM.getListEntries().getValue().get(position));
    }

    @Override
    public int getItemCount() {
        List<JournalEntry> ls = journalVM.getListEntries().getValue();
        return ls == null ? 0 : ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardJournalDetailBinding binding;
        private JournalEntry n3;

        public ViewHolder(CardJournalDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                journalVM.setEntry(n3.getId());
                Navigation.findNavController(v).navigate(R.id.action_contentMainFragment_to_journalDetailsEditFragment); //Navigation through fragments
            });
        }

        public JournalEntry getEntry() {
            return n3;
        }

        public void setEntry(JournalEntry n3) {
            this.n3 = n3;
            binding.setJournal(n3);
        }
    }
}
