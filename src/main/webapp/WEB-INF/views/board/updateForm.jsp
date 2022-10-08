<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${boards.id}">
        <div class="form-group">
            <input value=${boards.title} type="text" name="title" class="form-control" placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <label class="mr-sm-2" for="selectCategory">Category</label>
            <select class="custom-select mr-sm-2" id="selectCategory">
                <option selected>${boards.category}</option>
            </select>
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${boards.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">Save</button>

</div>

<script>
    $('.summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다',//placeholder 설정
        pasteHTML: '${boards.content}'
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>