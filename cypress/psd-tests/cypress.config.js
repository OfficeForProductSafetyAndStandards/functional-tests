const { defineConfig } = require("cypress");

module.exports = defineConfig({
  projectId: 'jx5f62',
      screenshotOnRunFailure: true,
      screenshotsFolder: 'cypress/screenshots',
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here

      
    },
  },
});
