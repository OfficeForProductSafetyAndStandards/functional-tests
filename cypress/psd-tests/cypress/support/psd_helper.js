export class psdhelper {

    verify_link_visible(link_text)
    {
     cy.contains("a", link_text).should('be.visible')
    }
    
    verify_link_not_visible(link_text)
    {
        cy.contains("a",link_text).should('not.exist')
    }
    login_as_psd_user()
    //ts user login details to be updated before running the tests
    {
        cy.signIn('xxx','xxx')
    }
    login_as_opss_user()
    {
        cy.signIn('xxx','xxx')
    }
    create_opss_case_allegation(source_type,category,hazard_type)
    {
        cy.contains('Create a case').click()
       cy.verify_h1('Create new')
       cy.select_radio('Product safety allegation')
       cy.click_continue()
     cy.verify_h1('New allegation')
     cy.select_radio(source_type)
     cy.click_continue()
     //enter source details 
     cy.get('#complainant_name').type('auto-test source name')
     cy.get('#complainant_phone_number').type('0789990809')
     cy.get('#complainant_email_address').type('auto-test@example.com')
     cy.click_continue()
     cy.verify_h1('New allegation')
     cy.get('#allegation_description').type('Auto test - opss allegation')
     cy.get('#allegation_product_category').type(category,'{enter}')
     cy.get('#allegation_hazard_type').type(hazard_type,'{enter}')
     cy.contains('Create allegation').click()
     cy.verify_success_banner('\n        Allegation was successfully created\n      ')
    }
    add_a_product(id,product_name)
    {
    cy.contains('Products (').click()
    cy.contains('Add a product to the case').click()
    cy.get('#reference').type(id)
    cy.contains('Continue').click()
    cy.verify_h2(product_name)
    cy.select_radio('Yes')
    cy.contains('Save and continue').click()
    cy.verify_success_banner('The product record was added to the case')
    }

    //search for a product
    search_product(product_name)
    {
    cy.contains('Products').click()
    cy.contains('All products - Search').click()
    cy.get('#q').type(product_name)
    cy.get('.govuk-button').eq(2).click()
    }
    create_a_product(prod_cat,sub_cat,prod_name)
    {
        cy.contains('Products').click()
        cy.contains('Create a product record').click()
        cy.verify_h1('Create a product record')
        cy.get('#category').select(prod_cat)
        cy.get('#subcategory').type(sub_cat)
        cy.select_radio('No') // counter fiet
        cy.get('#has_markings-1').click()
       // cy.get('#product_brand').type('Fisher price')
        cy.get('#name').type(prod_name)
        cy.get('#when_placed_on_market-1').click()
        cy.contains('Save').click()
        cy.verify_success_panel('Product record created')
    }



    ts_create_a_case(ref_number)
    {
    cy.contains('Create a new case for this product').click()
    cy.contains('A product is of concern').click()
    cy.contains('Continue').click()
    cy.contains('The product is unsafe (or suspected of being)').click()
    cy.get('#hazard_type').select('Asphyxiation')
    cy.get('#hazard_description').type('Auto test - hazard description')
    cy.contains('Continue').click()
    cy.contains('Yes').click()
    cy.get('#complainant_reference').type(ref_number)
    cy.contains('Continue').click()
    const uuid = () => Cypress._.random(0, 1e6)
    const id = uuid()
    const testname = `Auto test${id}`  
    cy.get('#user_title').type(testname)
    cy.contains('Save').click()
    cy.verify_success_panel('Case created')
    }

Add_supporting_info(type)
{
   
    cy.get('a[href*= "accident_or_incidents_type/new"]').click()
}
add_accident_incident(type)
{
    cy.get('a[href*="supporting-information"]').click()
    cy.get('a[href*= "accident_or_incidents_type/new"]').click()
    cy.select_radio(type)
    cy.click_continue()
    cy.verify_h1('Record an '+ type.toLowerCase())
    //cy.get('.govuk-body').contains('Spanish desk set')
    cy.get('#is_date_known').click()
    cy.get('.govuk-date-input__item').eq(0).type('2')
    cy.get('.govuk-date-input__item').eq(1).type('05')
    cy.get('.govuk-date-input__item').eq(2).type('2017')
    cy.select_radio('Normal use')
    cy.select_radio('Serious')
    cy.contains('Add '+type.toLowerCase()).click()
    cy.verify_success_banner('The supporting information has been updated.')
}
add_corrective_actions()
{
    cy.get('a[href*="corrective-actions/new"]').click()
    cy.select_radio('Making the marketing of the product subject to prior conditions')
    cy.get('.govuk-date-input__item').eq(0).type('2')
    cy.get('.govuk-date-input__item').eq(1).type('05')
    cy.get('.govuk-date-input__item').eq(2).type('2017')
    cy.get('#legislation').type('Carriage','{enter}')
    //cy.get('#has_online_recall_information-1').click()
    cy.select_radio('No')
    cy.get('[for="measure_type"]').contains('Yes').click()
    cy.get('[for="duration"]').contains('Permanent').click()
    cy.get('.govuk-checkboxes__item').contains('Local').click()
    cy.get('#related_file-1').click()   
    cy.click_continue()
    cy.verify_success_banner('The supporting information has been updated.')
    
}

}



export const psdHelper = new psdhelper()