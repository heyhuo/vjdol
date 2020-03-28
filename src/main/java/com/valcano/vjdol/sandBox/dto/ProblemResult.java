package com.valcano.vjdol.sandBox.dto;

import java.util.ArrayList;
import java.util.List;

public class ProblemResult {
    private int runId;
    private List<ProblemResultItem> resultItems = new ArrayList<ProblemResultItem>();

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public List<ProblemResultItem> getResultItems() {
        return resultItems;
    }

    public void setResultItems(List<ProblemResultItem> resultItems) {
        this.resultItems = resultItems;
    }

}

