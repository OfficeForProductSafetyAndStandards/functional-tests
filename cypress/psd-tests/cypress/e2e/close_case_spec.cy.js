/// <reference types="cypress" /> 
import { psdHelper } from "../support/psd_helper"

describe('Close case', ()=> {
    beforeEach(() => {
        cy.visit('https://psd:reviewAPP@psd-pr-2294.london.cloudapps.digital/') 
      })
it('Scenario: delete a case which has no products',()=> {
  psdHelper.login_as_opss_user()
  psdHelper.create_opss_case_allegation('A business','Communication and media equipment','Damage to hearing')
  cy.contains('Close case').click()
  cy.verify_h1('A product has not been added to this case')
  cy.contains('Delete the case').click()
  cy.verify_h1('Delete the case')
  cy.get('input[type="submit"]').click()
  cy.verify_success_banner('The case was deleted')

})
it('Scenario: should not delete a case which has product link',() => {
  psdHelper.login_as_opss_user()
  psdHelper.create_opss_case_allegation('A business','Communication and media equipment','Damage to hearing')
  psdHelper.add_a_product('11','Fibre Cement Exterior Wall Cladding Boards')
  cy.contains('Case ').click() 
  cy.contains('Close case').click()
  psdHelper.verify_link_not_visible('Delete the case')
  cy.verify_h1_label('Why are you closing the case?')
})

it('Scenario: close a case to freeze linked products', () => {
  psdHelper.login_as_opss_user()
  cy.contains('Your cases')
  cy.contains('Spanish desk set').click()
  cy.contains('Close case').click()
  cy.verify_h1_label('Why are you closing the case?')
  cy.get('#change_case_status_form_rationale').type('auto test - close case')
  cy.get('input[type="submit"]').click()
  cy.verify_success_banner('Allegation was closed')
  cy.contains('Products (').click()
  cy.verify_product_version_text('The PSD reference number for this version of the product record')
})

})