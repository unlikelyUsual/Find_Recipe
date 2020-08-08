$(document).ready(()=>{

    $('#addIngredient').on('click',(ev)=>{
        ev.preventDefault();
        const template = $('#ingredientTemplate').html();
        const container = $('#ingredientContainer');
        const range = $('#ingredientSize');
        let size = range.val() !== '' ? parseInt(range.val()) : 0 ;
        let string =template
            .replace(/{{amountName}}/g,"ingredients["+ size +"].amount")
            .replace(/{{uomName}}/g,"ingredients["+size +"].unitOfMeasure.description")
            .replace(/{{uomId}}/g,"ingredients["+size +"].unitOfMeasure.id")
            .replace(/{{descriptionName}}/g,"ingredients["+size +"].description");
        container.append(string);
        range.val(size++);

    });
});

function setId(select) {
    const $this = $(select);
    const id = $this.closest(".form-group").find('.uomId');
    id.val($this.find('option:selected').attr('id'));
}