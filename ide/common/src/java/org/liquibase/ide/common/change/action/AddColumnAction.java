package org.liquibase.ide.common.change.action;

import liquibase.change.AddColumnChange;
import liquibase.change.Change;
import liquibase.change.ColumnConfig;
import liquibase.database.structure.Column;
import liquibase.database.structure.Table;
import liquibase.database.structure.DatabaseObject;
import org.liquibase.ide.common.change.wizard.page.AddColumnWizardPage;
import org.liquibase.ide.common.change.wizard.page.AddColumnWizardPageImpl;
import org.liquibase.ide.common.change.wizard.page.RefactorWizardPage;
import org.liquibase.ide.common.change.wizard.RefactorWizard;

public class AddColumnAction extends BaseTableRefactorAction {

    public AddColumnAction() {
        super("Add Column");
    }

    public RefactorWizard createRefactorWizard(DatabaseObject dbObject) {
        return new RefactorWizard("Add column to "+ ((Table) dbObject).getName(), new AddColumnWizardPageImpl());
    }

    protected Change[] createChanges(Table selectedTable, RefactorWizardPage... pages) {
        AddColumnChange change = new AddColumnChange();
        change.setTableName(selectedTable.getName());

        AddColumnWizardPage wizardPage = (AddColumnWizardPage) pages[0];

        ColumnConfig config = new ColumnConfig();
        config.setName(wizardPage.getColumnName());
        config.setType(wizardPage.getColumnType());

        change.setColumn(config);
        return new Change[]{change};
    }
}
